import React, { Fragment } from 'react';
import { observer } from 'mobx-react';
import {
  Card,
  CardActionArea,
  CardContent,
  CardHeader, Grid, IconButton,
  List,
  ListItem,
  ListItemIcon,
  ListItemText, Typography, useTheme,
} from '@mui/material';
import {
  ContentCopyOutlined,
  ContentPaste, DescriptionTwoTone,
  Favorite, FavoriteBorder,
  FavoriteOutlined,
  LocationOn, NoteAlt, NoteAltOutlined,
  PhoneAndroid, PhoneIphoneTwoTone, Smartphone, TextSnippetOutlined
} from '@mui/icons-material';
import { AddressPage } from '../../../../api';
import { TextAvatar } from '../../../shared';
import { grey } from '@mui/material/colors';

const PersonalPageItemView = observer(
  ({
     personalPage,
     onDefault,
     onPersonalPage,
     onCopyIcon,
   }: {
    personalPage: AddressPage;
    onCopyIcon: (personalPageId: string) => void;
    onDefault: (personalPage: AddressPage) => void;
    onPersonalPage: (personalPage: AddressPage) => void;
  }) => {
    const defaultText = '-';
    const theme = useTheme();

    const handleClickPersonalPage = (personalPage: AddressPage) => () => onPersonalPage(personalPage);

    const handleClickDefault = (event: React.MouseEvent) => {
      onDefault(personalPage);
      event.stopPropagation();
      event.preventDefault();
    }
    const handleClickCopy = (event: React.MouseEvent) => {
      onCopyIcon(personalPage.id);
      event.stopPropagation();
      event.preventDefault();
    }

    return (
      <Card
        sx={{
          boxShadow: 'none',
          border: '1px lightgrey solid',
          backgroundColor: theme.palette.mode === 'light' ? '#f8f8f8' : '#222',
        }}
        elevation={3}
        onClick={handleClickPersonalPage(personalPage)}
      >
        <CardActionArea sx={{ p: 1 }}>
          <CardHeader
            avatar={<TextAvatar text={personalPage.name} sx={{ width: 64, height: 64, fontSize: '2em '}}/>}
            action={
              <Fragment>
                <IconButton onClick={handleClickCopy}>
                  <ContentCopyOutlined fontSize="small"/>
                </IconButton>
                <IconButton onClick={handleClickDefault}>
                  {personalPage.baseAddress ? (
                    <Favorite fontSize="small"/>
                  ) : (
                    <FavoriteBorder fontSize="small"/>
                  )}
                </IconButton>
              </Fragment>
            }
            title={personalPage.name}
            titleTypographyProps={{ variant: 'h6' }}
          />
          <CardContent sx={{ height: 120, overflow: 'auto', backgroundColor: theme.palette.mode === 'light' ? '#fff' : '#101212' }}>
            <Grid container spacing={2} alignItems="center">
              <Grid item xs={1}>
                <LocationOn fontSize="small" sx={{ color: 'lightgrey' }}/>
              </Grid>
              <Grid item xs={11}>
                <Typography
                  noWrap
                  variant="body2"
                  sx={{ color: 'gray', textOverflow: 'ellipsis' }}
                >
                  {personalPage.address?.fullAddress}
                </Typography>
              </Grid>

              <Grid item xs={1}>
                <PhoneIphoneTwoTone fontSize="small" sx={{ color: 'lightgrey' }}/>
              </Grid>
              <Grid item xs={11}>
                <Typography
                  noWrap
                  variant="body2"
                  sx={{ color: 'gray', textOverflow: 'ellipsis' }}
                >
                  {personalPage.phoneNumber}
                </Typography>
              </Grid>

              <Grid item xs={1}>
                <DescriptionTwoTone fontSize="small" sx={{ color: 'lightgrey' }}/>
              </Grid>
              <Grid item xs={11}>
                <Typography
                  noWrap
                  variant="body2"
                  sx={{ color: 'gray', textOverflow: 'ellipsis' }}
                >
                  {personalPage.memo}
                </Typography>
              </Grid>
            </Grid>
          </CardContent>
        </CardActionArea>
      </Card>
    );
  });

export default PersonalPageItemView;
