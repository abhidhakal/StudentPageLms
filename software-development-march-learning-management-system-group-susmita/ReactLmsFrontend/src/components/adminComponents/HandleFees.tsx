import React, { useEffect, useState } from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField,
    Paper,
    Button,
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

    useEffect(() => {
        const fetchNotices = async () => {
            try {
                const response = await axios.get('http://localhost:8080/notices/get');
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

    const filteredNotices = notices.filter(
        (notice) =>
            notice.title.toLowerCase().includes(filter.toLowerCase()) ||
            notice.description.toLowerCase().includes(filter.toLowerCase())
    );

    return (
        <main className="main-area">
            <div className="content">
                <div className="notices-display">
                    <Button variant="contained" className="add-notice-btn">
                        Add Notice
                    </Button>
                    <Button variant="contained" className="edit-notice-btn">
                        Edit Notice
                    </Button>
                    <Button variant="contained" className="delete-notice-btn">
                        Delete Notice
                    </Button>
                </div>

                <TextField
                    label="Filter Notices"
                    variant="outlined"
                    value={filter}
                    onChange={handleFilterChange}
                    style={{ marginBottom: '20px' }}
                />

                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>ID</TableCell>
                                <TableCell>Title</TableCell>
                                <TableCell>Description</TableCell>
                                <TableCell>Uploaded Date</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {filteredNotices.map((notice) => (
                                <TableRow key={notice.noticeId}>
                                    <TableCell>{notice.noticeId}</TableCell>
                                    <TableCell>{notice.title}</TableCell>
                                    <TableCell>{notice.description}</TableCell>
                                    <TableCell>{notice.uploadedDate}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </main>
    );
};

export default AddNotice;
