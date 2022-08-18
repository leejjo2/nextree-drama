import React, { Fragment, useEffect } from 'react';
import { observer, useLocalObservable } from 'mobx-react';
import { AddressPage } from '../../../api';
import { PersonalPageStateKeeper } from '../../../state';
import PersonalPageFormView from './view/PersonalPageFormView';
import PersonalPageButtonView from './view/PersonalPageButtonView';
import { Button } from '@mui/material';
import { useAuth } from '@nara/dock';
import { dialogUtil } from '@nara/prologue';

const PersonalPageFormContainer = observer(
  ({
     personalBookId,
     personalPageId,
     onCancel = () => undefined,
     onSuccess = () => undefined,
     onFail = () => undefined,
   }: {
    personalBookId?: string;
    personalPageId?: string;
    onCancel?: () => void;
    onSuccess?: () => void;
    onFail?: () => void;
  }) => {
    const personalPageStateKeeper = useLocalObservable(() => PersonalPageStateKeeper.instance);
    const { personalPage, fullAddress } = personalPageStateKeeper;

    useEffect(() => {
      init();
    }, []);

    const init = async () => {
      if (personalPageId) {
        await personalPageStateKeeper.findPersonalPageById(personalPageId);
      } else if (personalBookId) {
        personalPageStateKeeper.init(personalBookId);
      }
    };

    const handleReset = async () => {
      if (!personalPage) {
        return;
      }

      await personalPageStateKeeper.makeBasePersonalPage(personalPage);
      await init();
    };

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = event.target;
      personalPageStateKeeper.setPersonalPageProp(name, value);
    };

    const handleAddField = () => personalPageStateKeeper.addField();
    const handleRemoveField = (index: number) => personalPageStateKeeper.removeField(index);

    const handleChangeField = (index: number, event: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = event.target;
      personalPageStateKeeper.setPersonalPageField(index, name, value);
    };

    const handleSave = async () => {
      if (!personalPage) {
        return;
      }

      const response = personalPageId ?
        await personalPageStateKeeper.modify(personalPageId, AddressPage.asNameValues(personalPage))
        : await personalPageStateKeeper.add(personalPage);

      if (response.failed) {
        onFail();
      } else {
        onSuccess();
      }
    };

    const handleDelete = async () => {
      if (!personalPageId) {
        return;
      }

      if (!await dialogUtil.confirm('Are you sure you want to delete cotact?')) {
        return;
      }

      try {
        await personalPageStateKeeper.removePersonalPage(personalPageId);
      } catch (e) {
        onFail();
        return;
      }

      onSuccess();
    };

    return personalPage && (
      <Fragment>
        <PersonalPageFormView
          personalPage={personalPage}
          fullAddress={fullAddress}
          onReset={handleReset}
          onChange={handleChange}
          onChangeField={handleChangeField}
          onAddField={handleAddField}
          onRemoveField={handleRemoveField}
        />
        <PersonalPageButtonView
          onSave={handleSave}
          onCancel={onCancel}
          renderDeleteButton={personalPageId &&
            <Button variant="contained" color="error" onClick={handleDelete}>Delete</Button>}
        />
      </Fragment>
    );
  });

export default PersonalPageFormContainer;
