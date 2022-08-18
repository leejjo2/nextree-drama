import { ComponentMeta, ComponentStory } from '@storybook/react';
import * as React from 'react';
import { useDock } from '@nara/dock';
import { PersonalPageList } from '../../../../comp';
import { Workspace, WorkspaceType } from '../../../../comp/view/shared/types/Workspace';

export default {
  title: 'view/address/personal-book',
  component: PersonalPageList,
} as ComponentMeta<typeof PersonalPageList>;

const Template: ComponentStory<typeof PersonalPageList> = () => {
  const dock = useDock();

  const cinerooms: Workspace[] =
    dock.activeDock?.cinerooms.map((value) => ({
      id: value.cineroom.id,
      name: value.cineroom.name,
      type: WorkspaceType.Cineroom
    })) || [];

  return (
    <PersonalPageList
      personalBookId="1@1:1:1"
      cinerooms={cinerooms}
    />
  );
};

export const list = Template.bind({});
list.args = {};
