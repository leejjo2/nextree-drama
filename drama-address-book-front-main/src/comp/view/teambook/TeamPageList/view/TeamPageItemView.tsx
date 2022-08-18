import React, { Fragment } from 'react';
import { observer } from 'mobx-react';
import { Card, CardActionArea, CardContent, CardHeader, Grid, IconButton, Typography, useTheme } from '@mui/material';
import { DescriptionTwoTone, Favorite, FavoriteBorder, LocationOn, PhoneIphoneTwoTone } from '@mui/icons-material';
import { AddressPage } from '../../../../api';
import { TextAvatar } from '../../../shared';

const TeamPageItemView = observer(
  ({
    teamPage,
    onDefault,
    onTeamPage,
  }: {
    teamPage: AddressPage;
    onDefault: (teamPage: AddressPage) => void;
    onTeamPage: (teamPage: AddressPage) => void;
  }) => {
    const defaultText = '-';
    const theme = useTheme();

    const handleClickPersonalPage = (teamPage: AddressPage) => () => onTeamPage(teamPage);

    const handleClickDefault = (event: React.MouseEvent) => {
      onDefault(teamPage);
      event.stopPropagation();
      event.preventDefault();
    }

    return (
      <Card
        sx={{
          maxWidth: 420,
          boxShadow: 'none',
          border: '1px lightgrey solid',
          backgroundColor: theme.palette.mode === 'light' ? '#f8f8f8' : '#222',
        }}
        elevation={3}
        onClick={handleClickPersonalPage(teamPage)}
      >
        <CardActionArea sx={{ p: 1 }}>
          <CardHeader
            avatar={<TextAvatar text={teamPage.name} sx={{ width: 64, height: 64, fontSize: '1.2em '}}/>}
            action={
              <Fragment>
                <IconButton onClick={handleClickDefault}>
                  {teamPage.baseAddress ? (
                    <Favorite fontSize="small"/>
                  ) : (
                    <FavoriteBorder fontSize="small"/>
                  )}
                </IconButton>
              </Fragment>
            }
            title={teamPage.name}
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
                  {teamPage.address?.fullAddress}
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
                  {teamPage.phoneNumber}
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
                  {teamPage.memo}
                </Typography>
              </Grid>
            </Grid>
          </CardContent>
        </CardActionArea>
      </Card>
    );
  });

export default TeamPageItemView;
