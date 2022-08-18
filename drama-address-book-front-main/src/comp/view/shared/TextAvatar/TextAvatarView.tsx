import React from 'react';
import { Avatar, AvatarProps } from '@mui/material';
import { observer } from 'mobx-react';

const TextAvatarView = observer(
  ({
     text,
     ...avatarProps
   }: {
    text: string;
  } & AvatarProps) => {

    const genColor = (targetText: string) => {
      let hash = 0;
      let i;

      for (i = 0; i < targetText.length; i += 1) {
        hash = targetText.charCodeAt(i) + ((hash << 5) - hash);
      }

      let color = '#';

      for (i = 0; i < 3; i += 1) {
        const value = (hash >> (i * 8)) & 0xff;
        color += `00${value.toString(16)}`.slice(-2);
      }

      return color;
    };

    const texts = text.split(' ');

    const { sx, ...props} = avatarProps;

    return (
      <Avatar
        // @ts-ignore
        sx={{ ...sx, bgcolor: genColor(text), textTransform: 'upperCase' }}
        {...props}
      >
        {(texts[0][0] || '')}
        {(texts[1] ? texts[1][0] || '' : '')}
      </Avatar>
    );
  });

export default TextAvatarView;
