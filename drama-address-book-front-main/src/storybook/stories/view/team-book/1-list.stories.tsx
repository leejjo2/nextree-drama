import { ComponentMeta, ComponentStory } from '@storybook/react';
import * as React from 'react';
import { TeamPageList } from '../../../../comp';

export default {
  title: 'view/address/team-book',
  component: TeamPageList,
} as ComponentMeta<typeof TeamPageList>;

const Template: ComponentStory<typeof TeamPageList> = () => {
  return (
    <TeamPageList teamBookId="1:1:1:1" onTeamPage={() => undefined} />
  );
};

export const list = Template.bind({});
list.args = {};
