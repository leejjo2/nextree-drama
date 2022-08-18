import React, { useEffect } from 'react';
import { observer, useLocalObservable } from 'mobx-react';
import PersonalPageDetailView from './view/PersonalPageDetailView';
import { PersonalPageStateKeeper } from '../../../state';
import { DramaException } from '@nara/accent';

const PersonalPageDetailContainer = observer(
  ({
    personalPageId,
  }: {
    personalPageId: string
  }) => {
    const personalPageStateKeeper = useLocalObservable(() => PersonalPageStateKeeper.instance);
    const { personalPage, fullAddress } = personalPageStateKeeper;

    useEffect(() => {
      init();
    }, []);

    const init = () => {
      personalPageStateKeeper.findPersonalPageById(personalPageId);
    };

    const handleDefault = async () => {
      if (!personalPage) {
        throw new DramaException('PersonalPageDetail', 'personalPage should not be null.');
      }

      await personalPageStateKeeper.makeBasePersonalPage(personalPage);
      init();
    };

    return personalPage && (
      <PersonalPageDetailView
        personalPage={personalPage}
        fullAddress={fullAddress}
        onDefault={handleDefault}
      />
    );
  });

export default PersonalPageDetailContainer;
