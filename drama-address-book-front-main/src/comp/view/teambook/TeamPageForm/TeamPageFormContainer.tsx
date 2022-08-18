import { observer, useLocalObservable } from 'mobx-react';
import React, { Fragment, useEffect } from 'react';
import TeamPageFormView from './view/TeamPageFormView';
import TeamPageButtonView from './view/TeamPageButtonView';
import { AddressPage } from '../../../api';
import { Button } from '@mui/material';
import { TeamPageStateKeeper } from '../../../state/teambook';
import { dialogUtil } from '@nara/prologue';

const TeamPageFormContainer = observer(
  ({
     personalBookId,
     teamPageId,
     onCancel = () => undefined,
     onSuccess = () => undefined,
     onFail = () => undefined,
   }: {
    personalBookId?: string;
    teamPageId?: string;
    onCancel?: () => void;
    onSuccess?: () => void;
    onFail?: () => void;
  }) => {
    const teamPageStateKeeper = useLocalObservable(() => TeamPageStateKeeper.instance);
    const { teamPage, fullAddress } = teamPageStateKeeper;

    useEffect(() => {
      init();
    }, []);

    const init = async () => {
      if (teamPageId) {
        await teamPageStateKeeper.findTeamPageById(teamPageId);
      } else if (personalBookId) {
        teamPageStateKeeper.init(personalBookId);
      }
    };

    const handleReset = async () => {
      if (!teamPage) {
        return;
      }

      await teamPageStateKeeper.makeBaseTeamPage(teamPage);
      await init();
    };

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = event.target;
      teamPageStateKeeper.setTeamPageProp(name, value);
    };

    const handleAddField = () => teamPageStateKeeper.addField();
    const handleRemoveField = (index: number) => teamPageStateKeeper.removeField(index);

    const handleChangeField = (index: number, event: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = event.target;
      teamPageStateKeeper.setTeamPageField(index, name, value);
    };

    const handleSave = async () => {
      if (!teamPage) {
        return;
      }

      const response = teamPageId ?
        await teamPageStateKeeper.modify(teamPageId, AddressPage.asNameValues(teamPage))
        : await teamPageStateKeeper.add(teamPage);

      if (response.failed) {
        onFail();
      } else {
        onSuccess();
      }
    };

    const handleDelete = async () => {
      if (!teamPageId) {
        return;
      }

      if (!await dialogUtil.confirm('Are you sure you want to delete cotact?')) {
        return;
      }

      try {
        await teamPageStateKeeper.removeTeamPage(teamPageId);
      } catch (e) {
        onFail();
        return;
      }

      onSuccess();
    };

    return teamPage && (
      <Fragment>
        <TeamPageFormView
          teamPage={teamPage}
          fullAddress={fullAddress}
          onReset={handleReset}
          onChange={handleChange}
          onChangeField={handleChangeField}
          onAddField={handleAddField}
          onRemoveField={handleRemoveField}
        />
        <TeamPageButtonView
          onSave={handleSave}
          onCancel={onCancel}
          renderDeleteButton={teamPageId &&
            <Button variant="contained" color="error" onClick={handleDelete}>Delete</Button>}
        />
      </Fragment>
    );
  });

export default TeamPageFormContainer;
