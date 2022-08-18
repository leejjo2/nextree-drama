import { observer, useLocalObservable } from 'mobx-react';
import React, { Fragment, useEffect } from 'react';
import { Box, Grid, Pagination } from '@mui/material';
import { AddressPage } from '../../../api';
import { TeamPagesStateKeeper, TeamPageStateKeeper } from '../../../state/teambook';
import TeamPageSortView from './view/TeamPageSortView';
import TeamPageItemView from './view/TeamPageItemView';
import TeamPageEmptyView from './view/TeamPageEmptyView';

const TeamPageListContainer = observer(
  ({
    onTeamPage = () => undefined,
    onRegister = () => undefined,
    teamBookId,
  }: {
    onTeamPage: (teamPage: AddressPage) => void;
    onRegister?: () => void;
    teamBookId: string;
  }) => {
    const teamPageStateKeeper = useLocalObservable(() => TeamPageStateKeeper.instance);
    const teamPagesStateKeeper = useLocalObservable(() => TeamPagesStateKeeper.instance);
    const { teamPages, offset, totalCount } = teamPagesStateKeeper;

    useEffect(() => {
      init();
    }, []);

    const init = () => {
      teamPagesStateKeeper.setTeamBookId(teamBookId);
      teamPagesStateKeeper.findTeamPages(teamBookId);
    };

    const handleDefault = async (teamPage: AddressPage) => {
      await teamPageStateKeeper.makeBaseTeamPage(teamPage);
      await teamPagesStateKeeper.findTeamPages(teamPage.addressBookId);
    };

    const handleChangePage = (event: React.ChangeEvent<unknown>, page: number) => {
      teamPagesStateKeeper.changePage(page);
      teamPagesStateKeeper.findTeamPages(teamBookId);
    };

    const handleChangeSortingField = (name: keyof AddressPage) => {
      teamPagesStateKeeper.setOffsetProp('sortingField', name);
      teamPagesStateKeeper.findTeamPages(teamBookId);
    };

    const handleRegister = () => onRegister();
    const handleTeamPage = (teamPage: AddressPage) => onTeamPage(teamPage);

    return teamPages.length ? (
      <Fragment>
        <TeamPageSortView onChangeSrotingField={handleChangeSortingField}/>
        <Grid container spacing={5}>
          {teamPages.map((teamPage) => (
            <Grid item xs={4} key={teamPage.id}>
              <TeamPageItemView
                teamPage={teamPage}
                onDefault={handleDefault}
                onTeamPage={handleTeamPage}
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
      </Fragment>
    ) : (
      <TeamPageEmptyView
        onRegister={handleRegister}
      />
    );
  });

export default TeamPageListContainer;
