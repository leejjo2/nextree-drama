import { ComponentMeta, ComponentStory } from '@storybook/react';
import * as React from 'react';
import { PersonalPageForm } from '../../../../comp';
import { personalPageId } from '../../data/testData';

export default {
  title: 'view/address/personal-book',
  component: PersonalPageForm,
} as ComponentMeta<typeof PersonalPageForm>;

const Registration: ComponentStory<typeof PersonalPageForm> = () => {
  return (
    <PersonalPageForm
      personalBookId="1@1:1:1"
    />
  );
};

export const registration = Registration.bind({});
registration.args = {};

const Modification: ComponentStory<typeof PersonalPageForm> = () => {
  return (
    <PersonalPageForm
      personalPageId={personalPageId}
    />
  );
};

export const modification = Modification.bind({});
modification.args = {};

