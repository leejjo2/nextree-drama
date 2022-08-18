import React, { useEffect } from 'react';
import { observer, useLocalObservable } from 'mobx-react';
import { DramaException } from '@nara/accent';
import TeamPageDetailView from './view/TeamPageDetailView';
import { TeamPageStateKeeper } from '../../../state/teambook';

const TeamPageDetailContainer = observer(
  ({
    teamPageId,
  }: {
    teamPageId: string;
  }) => {
    const teamPageStateKeeper = useLocalObservable(() => TeamPageStateKeeper.instance);
    const { teamPage, fullAddress } = teamPageStateKeeper;

    useEffect(() => {
      init();
    }, []);

    const init = () => {
      teamPageStateKeeper.findTeamPageById(teamPageId);
    };

    const handleDefault = async () => {
      if (!teamPage) {
        throw new DramaException('TeamPageDetail', 'teamPage should not be null.');
      }

      await teamPageStateKeeper.makeBaseTeamPage(teamPage);
      init();
    };

    return teamPage && (
      <TeamPageDetailView
        teamPage={teamPage}
        fullAddress={fullAddress}
        onDefault={handleDefault}
      />
    );
  });

export default TeamPageDetailContainer;
