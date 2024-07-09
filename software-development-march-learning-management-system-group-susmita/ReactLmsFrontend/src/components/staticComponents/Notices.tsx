import React, { useEffect, useState } from 'react';
import {
    Grid,
    TextField,
    Paper,
    Button,
    Typography,
    Box,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle
} from '@mui/material';
import '../styles/main.css';
import axios from 'axios';

interface Notice {
    noticeId: number;
    title: string;
    description: string;
    uploadedDate: string;  // Changed to string for simplicity
}

const AddNotice: React.FC = () => {
    const [notices, setNotices] = useState<Notice[]>([]);
    const [filter, setFilter] = useState<string>('');
    const [openDialog, setOpenDialog] = useState<boolean>(false);
    const [currentNotice, setCurrentNotice] = useState<Partial<Notice>>({});

    useEffect(() => {
        const fetchNotices = async () => {
            try {
                const response = await axios.get('http://localhost:8080/notice/get');
                console.log('API response data:', response.data);  // Log response data
                if (response.data && Array.isArray(response.data.data)) {
                    const transformedData: Notice[] = response.data.data.map((notice: any) => ({
                        noticeId: notice.noticeId,
                        title: notice.title,
                        description: notice.description,
                        uploadedDate: new Date(notice.uploadedDate).toLocaleDateString(),
                    }));
                    setNotices(transformedData);
                } else {
                    console.error('Expected an array in data property but got:', response.data);
                }
            } catch (error) {
                console.error('Error fetching notices:', error);
            }
        };
        fetchNotices();
    }, []);

    const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFilter(event.target.value);
    };

    const handleDialogOpen = (notice: Partial<Notice> = {}) => {
        setCurrentNotice(notice);
        setOpenDialog(true);
    };

    const handleDialogClose = () => {
        setOpenDialog(false);
        setCurrentNotice({});
    };

    const handleSaveNotice = async () => {
        if (currentNotice.noticeId) {
            // Edit existing notice
            try {
                const response = await axios.put(`http://localhost:8080/notice/update/${currentNotice.noticeId}`, currentNotice);
                console.log('Notice edited:', response.data);
                setNotices((prevNotices) =>
                    prevNotices.map((notice) =>
                        notice.noticeId === currentNotice.noticeId ? { ...notice, ...currentNotice } : notice
                    )
                );
            } catch (error) {
                console.error('Error editing notice:', error);
            }
        } else {
            // Add new notice
            try {
                const response = await axios.post('http://localhost:8080/notice/save', currentNotice);
                console.log('Notice added:', response.data);
                const newNotice = { ...currentNotice, noticeId: response.data.noticeId } as Notice;
                setNotices((prevNotices) => [...prevNotices, newNotice]);
            } catch (error) {
                console.error('Error adding notice:', error);
            }
        }
        handleDialogClose();
    };

    const handleDeleteNotice = async (noticeId: number) => {
        try {
            await axios.delete(`http://localhost:8080/notice/delete/${noticeId}`);
            setNotices((prevNotices) => prevNotices.filter((notice) => notice.noticeId !== noticeId));
        } catch (error) {
            console.error('Error deleting notice:', error);
        }
    };

    const filteredNotices = notices.filter(
        (notice) =>
            notice.title.toLowerCase().includes(filter.toLowerCase()) ||
            notice.description.toLowerCase().includes(filter.toLowerCase())
    );

    return (
        <main className="main-area">
            <div className="content">
                <TextField
                    label="Filter Notices"
                    variant="outlined"
                    value={filter}
                    onChange={handleFilterChange}
                    style={{ marginBottom: '20px' }}
                />

                <Grid container spacing={3}>
                    {filteredNotices.map((notice) => (
                        <Grid item xs={12} sm={6} md={4} key={notice.noticeId}>
                            <Paper elevation={3} style={{ padding: '20px', height: '100%' }}>
                                <Typography variant="h6" gutterBottom>
                                    {notice.title}
                                </Typography>
                                <Typography variant="body2" color="textSecondary" gutterBottom>
                                    {notice.description}
                                </Typography>
                                <Box mt={2}>
                                    <Typography variant="caption" color="textSecondary">
                                        Uploaded Date: {notice.uploadedDate}
                                    </Typography>
                                </Box>
                                <Box mt={2}>
                                    <Button variant="contained" color="primary" onClick={() => handleDialogOpen(notice)}>
                                        Edit
                                    </Button>
                                    <Button variant="contained" color="secondary" onClick={() => handleDeleteNotice(notice.noticeId)}>
                                        Delete
                                    </Button>
                                </Box>
                            </Paper>
                        </Grid>
                    ))}
                </Grid>

                <div className="notices-actions" style={{ marginTop: '20px' }}>
                    <Button variant="contained" onClick={() => handleDialogOpen()}>
                        Add Notice
                    </Button>
                </div>

                <Dialog open={openDialog} onClose={handleDialogClose}>
                    <DialogTitle>{currentNotice.noticeId ? 'Edit Notice' : 'Add Notice'}</DialogTitle>
                    <DialogContent>
                        <TextField
                            autoFocus
                            margin="dense"
                            label="Title"
                            type="text"
                            fullWidth
                            value={currentNotice.title || ''}
                            onChange={(e) => setCurrentNotice({ ...currentNotice, title: e.target.value })}
                        />
                        <TextField
                            margin="dense"
                            label="Description"
                            type="text"
                            fullWidth
                            value={currentNotice.description || ''}
                            onChange={(e) => setCurrentNotice({ ...currentNotice, description: e.target.value })}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleDialogClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={handleSaveNotice} color="primary">
                            Save
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </main>
    );
};

export default AddNotice;
