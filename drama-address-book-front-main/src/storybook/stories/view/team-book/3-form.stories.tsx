import { TeamPageForm } from '../../../../comp';
import { ComponentMeta, ComponentStory } from '@storybook/react';
import * as React from 'react';
import { teamPageId } from '../../data/testData';

export default {
  title: 'view/address/team-book',
  component: TeamPageForm,
} as ComponentMeta<typeof TeamPageForm>;

const Registration: ComponentStory<typeof TeamPageForm> = () => {
  return (
    <TeamPageForm
      teamBookId='1:1:1:1'
    />
  );
};

export const registration = Registration.bind({});
registration.args = {};

const Modification: ComponentStory<typeof TeamPageForm> = () => {
  return (
    <TeamPageForm
      teamPageId={teamPageId}
    />
  );
};

export const modification = Modification.bind({});
modification.args = {};
