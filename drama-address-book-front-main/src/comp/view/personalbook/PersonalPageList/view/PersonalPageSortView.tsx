import React from 'react';
import { Button, Grid } from '@mui/material';
import { AddressPage } from '../../../../api';

const PersonalPageSortView =
  ({
     onChangeSrotingField,
   }: {
    onChangeSrotingField: (name: keyof AddressPage) => void;
  }) => {
    const handleClickSort = (name: keyof AddressPage) => () => onChangeSrotingField(name);

    return (
      <Grid container spacing={2} display="flex" alignItems="center" sx={{ mb: 2 }}>
        <Grid item>
          <Button color="inherit" sx={{ textTransform: 'none' }} onClick={handleClickSort('registrationTime')}>
            Sort by: Added
          </Button>
        </Grid>
        <Grid item>
          <Button color="inherit" sx={{ textTransform: 'none' }} onClick={handleClickSort('name')}>
            Sort by: Name
          </Button>
        </Grid>
      </Grid>
    );
  };

export default PersonalPageSortView;
