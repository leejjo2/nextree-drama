import React from 'react';
import { observer } from 'mobx-react';
import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  IconButton,
  InputLabel,
  MenuItem,
  Select,
} from '@mui/material';
import { CloseOutlined } from '@mui/icons-material';
import { Workspace } from '../../../shared';

const CopyPersonalPageDialogView = observer(
  ({
     open,
     cinerooms,
     onClose,
     onChangeTeam,
     onCopy,
   }: {
    open: boolean,
    cinerooms: Workspace[]
    onClose: () => void;
    onCopy: () => void;
    onChangeTeam: (event: React.ChangeEvent<HTMLInputElement>) => void;
  }) => {
    const title = 'Copy Team Contact'
    const handleClickCopy = () => onCopy();
    const handleChangeTeam = () => (event: React.ChangeEvent<HTMLInputElement>) => {
      console.log(event);
      onChangeTeam(event);
    };

    return (
      <Dialog open={open} maxWidth="md">
        <DialogTitle>
          <Box display="flex" justifyContent="space-between">
            {title}
            <IconButton onClick={onClose}><CloseOutlined fontSize="small"/></IconButton>
          </Box>
        </DialogTitle>
        <DialogContent>
          <Box display="flex" justifyContent="center">
            <FormControl sx={{ m: 1, minWidth: 300 }} size="small">
              <InputLabel id="demo-select-small">Team</InputLabel>
              <Select
                labelId="demo-select-small"
                id="demo-select-small"
                label="Team"
                fullWidth
                onChange={handleChangeTeam}
              >
                {cinerooms.map(value => <MenuItem value={value.id}>{value.name}</MenuItem>)}
              </Select>
            </FormControl>
          </Box>
        </DialogContent>
        <DialogActions>
          <Box mr={2} mb={2} ml={2}>
            <Button
              color="primary"
              onClick={onClose}
            >
              Cancel
            </Button>
            <Button
              color="primary"
              onClick={handleClickCopy}
            >
              OK
            </Button>
          </Box>
        </DialogActions>
      </Dialog>
    );
  });

export default CopyPersonalPageDialogView;

