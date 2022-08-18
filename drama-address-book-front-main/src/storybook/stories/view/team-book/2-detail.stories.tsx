import * as React from 'react';
import { TeamPageDetail } from '../../../../comp';
import { ComponentMeta, ComponentStory } from '@storybook/react';
import { teamPageId } from '../../data/testData';

export default {
  title: 'view/address/team-book',
  component: TeamPageDetail,
} as ComponentMeta<typeof TeamPageDetail>;

const Template: ComponentStory<typeof TeamPageDetail> = () => {
  return (
    <TeamPageDetail
      teamPageId={teamPageId}
    />
  );
};

export const detail = Template.bind({});
detail.args = {};
