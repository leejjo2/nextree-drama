import React, { Fragment, useState } from 'react';
import { observer } from 'mobx-react';
import { Box, Button, Grid, Typography } from '@mui/material';
import { AddressPage } from '../../../../api';
import { KeyboardArrowDown, KeyboardArrowUp } from '@mui/icons-material';
import { TextAvatar } from '../../../shared';

const PersonalPageDetailView = observer(
  ({
    personalPage,
    fullAddress,
    onDefault,
  }: {
    personalPage: AddressPage;
    fullAddress: string;
    onDefault: () => void;
  }) => {
    const defaultText = '-';
    const [expanded, setExpanded] = useState(false);

    const handleToggleExpand = () => setExpanded(!expanded);

    return (
      <Grid container spacing={2}>
        <Grid item xs={4}>
          <Box width="100%" display="flex" justifyContent="flex-end">
            <TextAvatar  text={personalPage.name} sx={{ width: 200, height: 200, fontSize: '6em' }}/>
          </Box>
        </Grid>
        <Grid item xs={8} display="flex" alignItems="end">
          <Typography variant="h5">{personalPage.name}</Typography>
        </Grid>
        <Grid item xs={12} sx={{ py: 5 }}/>

        {/* basic info */}

        <Grid item xs={12}>
          <Typography variant="h6">Basic Info</Typography>
        </Grid>
        <Grid item xs={4}>
          <Typography variant="body1">Address</Typography>
        </Grid>
        <Grid item xs={8}>
          <Grid container spacing={5}>
            <Grid item xs={12}>
              <Box display="flex" alignItems="center" justifyContent="space-between">
                <Typography variant="body1">{fullAddress|| defaultText}</Typography>
                <Button
                  color="primary"
                  startIcon={expanded ? <KeyboardArrowUp fontSize="small"/> : <KeyboardArrowDown fontSize="small"/>}
                  onClick={handleToggleExpand}
                >
                  Show More
                </Button>
              </Box>
            </Grid>

            {expanded && (
              <Fragment>
                <Grid item xs={2}>
                  <Typography variant="body2">Postal code</Typography>
                </Grid>
                <Grid item xs={10}>
                  <Typography variant="body1">{personalPage.address?.zipCode || defaultText}</Typography>
                </Grid>

                <Grid item xs={2}>
                  <Typography variant="body2">City</Typography>
                </Grid>
                <Grid item xs={10}>
                  <Typography variant="body1">{personalPage.address?.city || defaultText}</Typography>
                </Grid>

                <Grid item xs={2}>
                  <Typography variant="body2">State</Typography>
                </Grid>
                <Grid item xs={10}>
                  <Typography variant="body1">{personalPage.address?.state || defaultText}</Typography>
                </Grid>

                <Grid item xs={2}>
                  <Typography variant="body2">Street</Typography>
                </Grid>
                <Grid item xs={10}>
                  <Typography variant="body1">{personalPage.address?.street || defaultText}</Typography>
                </Grid>

                <Grid item xs={2}>
                  <Typography variant="body2">Street Ext</Typography>
                </Grid>
                <Grid item xs={10}>
                  <Typography variant="body1">{personalPage.address?.zipAddress || defaultText}</Typography>
                </Grid>
              </Fragment>
            )}
          </Grid>
        </Grid>

        <Grid item xs={12}/>

        <Grid item xs={4}>
          <Typography variant="body1">Phone</Typography>
        </Grid>
        <Grid item xs={8}>
          <Typography variant="body1">{personalPage.phoneNumber || defaultText}</Typography>
        </Grid>

        <Grid item xs={4}>
          <Typography variant="body1">Note</Typography>
        </Grid>
        <Grid item xs={8}>
          <Typography variant="body1">{personalPage.memo || defaultText}</Typography>
        </Grid>
        <Grid item xs={12} sx={{ py: 5 }}/>

        {/* additional info */}

        <Grid item xs={12}>
          <Typography variant="h6">Additional Info</Typography>
        </Grid>

        {personalPage.fields.map((field) => (
          <Fragment key={field.name}>
            <Grid item xs={4}>
              <Typography variant="body1">{field.name}</Typography>
            </Grid>
            <Grid item xs={7.5}>
              <Typography variant="body1">{field.value || defaultText}</Typography>
            </Grid>
          </Fragment>
        ))}
      </Grid>
    );
  });

export default PersonalPageDetailView;
