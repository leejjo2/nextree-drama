import React, { Fragment, useEffect, useState } from 'react';
import { observer, useLocalObservable } from 'mobx-react';
import { PersonalPagesStateKeeper, PersonalPageStateKeeper } from '../../../state';
import { AddressPage } from '../../../api';
import { Box, Grid, Pagination } from '@mui/material';
import PersonalPageItemView from './view/PersonalPageItemView';
import PersonalPageSortView from './view/PersonalPageSortView';
import PersonalPageEmptyView from './view/PersonalPageEmptyView';
import CopyPersonalPageDialogView from './view/CopyPersonalPageDialogView';
import { Workspace } from '../../shared';

const PersonalPageListContainer = observer(
  ({
    cinerooms,
    personalBookId,
    onRegister = () => undefined,
    onPersonalPage = () => undefined,
  }: {
    cinerooms: Workspace[]
    personalBookId: string
    onRegister?: () => void;
    onPersonalPage?: (personalPage: AddressPage) => void;
  }) => {
    const [open, setOpen] = useState(false);
    const [selectedPersonalPageId, setSelectedPersonalPageId] = useState<string | undefined>(undefined);
    const [selectedTeamBookId, setSelectedTeamBookId] = useState<string | undefined>(undefined);

    const personalPageStateKeeper = useLocalObservable(() => PersonalPageStateKeeper.instance);
    const personalPagesStateKeeper = useLocalObservable(() => PersonalPagesStateKeeper.instance);
    const { personalPages, offset, totalCount } = personalPagesStateKeeper;

    useEffect(() => {
      init();
    }, []);

    const init = () => {
      personalPagesStateKeeper.setPersonalBookId(personalBookId);
      personalPagesStateKeeper.findPersonalPages(personalBookId);
    };

    const handleClose = () => setOpen(false);

    const handleCopyIcon = (personalPageId: string) => {
      setOpen(true);
      setSelectedPersonalPageId(personalPageId);
    };

    const handleChangeTeam = (event: React.ChangeEvent<HTMLInputElement>) => {
      setSelectedTeamBookId(event.target.value);
    };

    const handleCopy = () => {
      if (selectedPersonalPageId && selectedTeamBookId) {
        personalPageStateKeeper.copyPersonalPageFromTeamBook(selectedPersonalPageId, selectedTeamBookId);
      }
      setOpen(false);
      setSelectedPersonalPageId(undefined);
      setSelectedTeamBookId(undefined);
    };

    const handleDefault = async (personalPage: AddressPage) => {
      await personalPageStateKeeper.makeBasePersonalPage(personalPage);
      await personalPagesStateKeeper.findPersonalPages(personalPage.addressBookId);
    };

    const handleChangePage = (event: React.ChangeEvent<unknown>, page: number) => {
      personalPagesStateKeeper.changePage(page);
      personalPagesStateKeeper.findPersonalPages(personalBookId);
    };

    const handleChangeSortingField = (name: keyof AddressPage) => {
      personalPagesStateKeeper.setOffsetProp('sortingField', name);
      personalPagesStateKeeper.findPersonalPages(personalBookId);
    };

    const handleRegister = () => onRegister();
    const handlePersonalPage = (personalPage: AddressPage) => onPersonalPage(personalPage);

    return personalPages.length ? (
      <Fragment>
        <PersonalPageSortView onChangeSrotingField={handleChangeSortingField}/>
        <Grid container spacing={5}>
          {personalPages.map((personalPage) => (
            <Grid item xs={4} key={personalPage.id}>
              <PersonalPageItemView
                personalPage={personalPage}
                onDefault={handleDefault}
                onPersonalPage={handlePersonalPage}
                onCopyIcon={handleCopyIcon}
              />
            </Grid>
          ))}
        </Grid>
        <Box sx={{ width: '100%', mt: 5, display: 'flex', justifyContent: 'center' }}>
          <Pagination
            color="primary"
            shape="rounded"
            count={Math.ceil(totalCount / offset.limit)}
            onChange={handleChangePage}
          />
        </Box>
        <CopyPersonalPageDialogView
          open={open}
          cinerooms={cinerooms}
          onClose={handleClose}
          onChangeTeam={handleChangeTeam}
          onCopy={handleCopy}
        />
      </Fragment>
    ) : (
      <PersonalPageEmptyView
        onRegister={handleRegister}
      />
    );
  });

export default PersonalPageListContainer;
