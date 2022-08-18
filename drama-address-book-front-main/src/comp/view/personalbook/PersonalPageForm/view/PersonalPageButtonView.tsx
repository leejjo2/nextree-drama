import React, { ReactNode } from 'react';
import { Box, Button, Grid } from '@mui/material';

const PersonalPageButtonView =
  ({
     renderDeleteButton,
     onSave,
     onCancel,
   }: {
    renderDeleteButton: ReactNode;
    onSave: () => void;
    onCancel: () => void;
  }) => {
    return (
      <Box mt={5} display="grid" justifyContent="center" alignItems="center">
        <Grid container spacing={2}>
          <Grid item>
            <Button variant="outlined" onClick={onCancel}>Cancel</Button>
          </Grid>
          <Grid item>
            {renderDeleteButton}
          </Grid>
          <Grid item>
            <Button variant="contained" onClick={onSave}>Save</Button>
          </Grid>
        </Grid>
      </Box>
    );
  };

export default PersonalPageButtonView;
