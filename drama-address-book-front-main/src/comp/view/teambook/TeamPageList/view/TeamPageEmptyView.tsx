import { observer } from 'mobx-react';
import { Button, Grid, Typography, useTheme } from '@mui/material';
import { Add, ContactMailRounded } from '@mui/icons-material';
import React from 'react';
import { grey } from '@mui/material/colors';

const TeamPageEmptyView = observer(
  ({
     onRegister = () => undefined,
   }: {
    onRegister?: () => void;
  }) => {
    const theme = useTheme();

    const handleClickRegister = () => onRegister();

    return (
      <Grid container spacing={2}>
        <Grid
          item
          xs={12}
          display="flex"
          justifyContent="center"
        >
          <ContactMailRounded sx={{ fontSize: '10em', color: theme.palette.mode === 'light' ? grey[500] : grey[400] }}/>
        </Grid>
        <Grid
          item
          xs={12}
          display="flex"
          justifyContent="center"
          sx={{ textAlign: 'center' }}
        >
          <Typography variant="subtitle1" color={theme.palette.mode === 'light' ? grey[500] : grey[400]}>
            No contact found.<br/>Add your new contact.
          </Typography>
        </Grid>
        <Grid
          item
          xs={12}
          display="flex"
          justifyContent="center"
        >
          <Button
            variant="outlined"
            startIcon={<Add/>}
            onClick={handleClickRegister}>
            Add Contact
          </Button>
        </Grid>
      </Grid>
    );
  });

export default TeamPageEmptyView;
