import React, { Fragment } from 'react';
import { observer } from 'mobx-react';
import { Box, Button, Grid, IconButton, TextField, Typography, } from '@mui/material';
import { AddressPage } from '../../../../api';
import { Add, ClearOutlined } from '@mui/icons-material';
import { TextAvatar } from '../../../shared';

const PersonalPageFormView = observer(
  ({
     personalPage,
     fullAddress,
     onChange,
     onChangeField,
     onAddField,
     onRemoveField,
   }: {
    personalPage: AddressPage;
    fullAddress: string;
    onReset: () => void;
    onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    onChangeField: (index: number, event: React.ChangeEvent<HTMLInputElement>) => void;
    onAddField: () => void;
    onRemoveField: (index: number) => void;
  }) => {
    const defaultText = '';

    const handleChangeField = (index: number) => (event: React.ChangeEvent<HTMLInputElement>) => onChangeField(index, event);
    const handleClickRemoveField = (index: number) => () => onRemoveField(index);

    return (
      <Grid container spacing={2}>
        <Grid item xs={4}>
          <Box width="100%" display="flex" justifyContent="flex-end">
            <TextAvatar  text={personalPage.name} sx={{ width: 200, height: 200, fontSize: '6em' }}/>
          </Box>
        </Grid>
        <Grid item xs={8} display="flex" alignItems="end">
          <TextField
            required
            sx={{ width: 500 }}
            name="name"
            placeholder="Contact full name"
            value={personalPage.name}
            onChange={onChange}
          />
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
              <TextField
                fullWidth
                variant="standard"
                name="fullAddress"
                value={fullAddress|| defaultText}
                color="info"
                inputProps={
                  { readOnly: true }
                }
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                variant="standard"
                label="Postal code"
                name="address.zipCode"
                value={personalPage.address?.zipCode || defaultText}
                onChange={onChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                variant="standard"
                label="City"
                name="address.city"
                value={personalPage.address?.city || defaultText}
                onChange={onChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="State"
                variant="standard"
                name="address.state"
                value={personalPage.address?.state || defaultText}
                onChange={onChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                variant="standard"
                label="Street"
                name="address.street"
                value={personalPage.address?.street || defaultText}
                onChange={onChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                variant="standard"
                label="Street Ext"
                name="address.zipAddress"
                value={personalPage.address?.zipAddress || defaultText}
                onChange={onChange}
              />
            </Grid>
          </Grid>
        </Grid>

        <Grid item xs={12}/>

        <Grid item xs={4}>
          <Typography variant="body1">Phone</Typography>
        </Grid>
        <Grid item xs={8}>
          <TextField
            fullWidth
            name="phoneNumber"
            value={personalPage.phoneNumber || defaultText}
            onChange={onChange}
          />
        </Grid>

        <Grid item xs={4}>
          <Typography variant="body1">Note</Typography>
        </Grid>
        <Grid item xs={8}>
          <TextField
            fullWidth
            name="memo"
            multiline
            rows={3}
            value={personalPage.memo || defaultText}
            onChange={onChange}
          />
        </Grid>

        {/* additional info */}

        <Grid item xs={12}>
          <Box display="flex" justifyContent="space-between">
            <Typography variant="h6">Additional Info</Typography>
            <Button startIcon={<Add/>} onClick={onAddField}>
              Add Field
            </Button>
          </Box>
        </Grid>

        {personalPage.fields.map((field, index) => (
          <Fragment key={index}>
            <Grid item xs={4}>
              <TextField
                fullWidth
                name="name"
                placeholder="Field name"
                value={field.name || defaultText}
                onChange={handleChangeField(index)}
              />
            </Grid>
            <Grid item xs={7.5}>
              <TextField
                fullWidth
                name="value"
                placeholder="Field value"
                value={field.value || defaultText}
                onChange={handleChangeField(index)}
              />
            </Grid>
            <Grid
              item
              xs={0.5}
              display="flex"
              justifyContent="center"
            >
              <IconButton onClick={handleClickRemoveField(index)}>
                <ClearOutlined/>
              </IconButton>
            </Grid>
          </Fragment>
        ))}
      </Grid>
    );
  });

export default PersonalPageFormView;
