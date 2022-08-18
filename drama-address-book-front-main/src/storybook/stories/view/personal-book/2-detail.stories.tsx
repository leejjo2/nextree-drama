import { ComponentMeta, ComponentStory } from '@storybook/react';
import * as React from 'react';
import { PersonalPageDetail } from '../../../../comp';
import { personalPageId } from '../../data/testData';

export default {
  title: 'view/address/personal-book',
  component: PersonalPageDetail,
} as ComponentMeta<typeof PersonalPageDetail>;

const Template: ComponentStory<typeof PersonalPageDetail> = () => {
  return (
    <PersonalPageDetail
      personalPageId={personalPageId}
    />
  );
};

export const detail = Template.bind({});
detail.args = {};
