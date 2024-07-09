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
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
} from '@mui/material';
import axios from 'axios';

interface Teacher {
    id: number;
    teacherName: string;
    password: string;
    email: string;
    contacts: number;
    dateOfBirth: string;
    joinDate: string;
    age: number;
}

const AddTeacher: React.FC = () => {
    const [teachers, setTeachers] = useState<Teacher[]>([]);
    const [filter, setFilter] = useState<string>('');
    const [open, setOpen] = useState<boolean>(false);
    const [editMode, setEditMode] = useState<boolean>(false);
    const [currentTeacher, setCurrentTeacher] = useState<Teacher | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        fetchTeachers();
    }, []);

    const fetchTeachers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/teachers/get');
            if (response.data && Array.isArray(response.data.data)) {
                const transformedData: Teacher[] = response.data.data.map((teacher: any) => ({
                    id: teacher.id,
                    teacherName: teacher.teacherName,
                    password: teacher.password,
                    email: teacher.email,
                    contacts: teacher.contacts,
                    dateOfBirth: new Date(teacher.dateOfBirth).toLocaleDateString(),
                    joinDate: new Date(teacher.joinDate).toLocaleDateString(),
                    age: teacher.age,
                }));
                setTeachers(transformedData);
            } else {
                console.error('Expected an array in data property but got:', response.data);
            }
        } catch (error) {
            console.error('Error fetching teachers:', error);
        }
    };

    const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFilter(event.target.value);
    };

    const handleOpen = () => {
        setEditMode(false);
        setCurrentTeacher({
            id: 0,
            teacherName: '',
            password: '',
            email: '',
            contacts: 0,
            dateOfBirth: '',
            joinDate: '',
            age: 0,
        });
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setError(null);
    };

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setCurrentTeacher((prevTeacher) => prevTeacher ? { ...prevTeacher, [name]: value } : null);
    };

    const handleSubmit = async () => {
        if (currentTeacher) {
            try {
                setError(null);
                const payload = {
                    ...currentTeacher,
                    contacts: Number(currentTeacher.contacts),
                    age: Number(currentTeacher.age),
                };
                console.log('Submitting data:', payload);

                if (editMode) {
                    await axios.put(`http://localhost:8080/teachers/update/${currentTeacher.id}`, payload);
                } else {
                    await axios.post('http://localhost:8080/teachers/add', payload);
                }
                fetchTeachers();
                handleClose();
            } catch (error) {
                if (axios.isAxiosError(error) && error.response) {
                    setError(`Error: ${error.response.status} - ${error.response.data.message}`);
                } else {
                    setError('An unexpected error occurred.');
                }
                console.error('Error submitting teacher:', error);
            }
        }
    };

    const handleEdit = (teacher: Teacher) => {
        setEditMode(true);
        setCurrentTeacher(teacher);
        setOpen(true);
    };

    const handleDelete = async (id: number) => {
        try {
            await axios.delete(`http://localhost:8080/teachers/delete/${id}`);
            fetchTeachers();
        } catch (error) {
            console.error('Error deleting teacher:', error);
        }
    };

    const filteredTeachers = teachers.filter(
        (teacher) =>
            teacher.teacherName.toLowerCase().includes(filter.toLowerCase()) ||
            teacher.email.toLowerCase().includes(filter.toLowerCase())
    );

    return (
        <main className="main-area">
            <div className="content">
                <div className="teachers-display">
                    <Button variant="contained" className="add-teacher-btn" onClick={handleOpen}>
                        Add Teacher
                    </Button>
                </div>

                <TextField
                    label="Filter Teachers"
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
                                <TableCell>Name</TableCell>
                                <TableCell>Password</TableCell>
                                <TableCell>Email</TableCell>
                                <TableCell>Contacts</TableCell>
                                <TableCell>Date of Birth</TableCell>
                                <TableCell>Join Date</TableCell>
                                <TableCell>Age</TableCell>
                                <TableCell>Actions</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {filteredTeachers.map((teacher) => (
                                <TableRow key={teacher.id}>
                                    <TableCell>{teacher.id}</TableCell>
                                    <TableCell>{teacher.teacherName}</TableCell>
                                    <TableCell>{teacher.password}</TableCell>
                                    <TableCell>{teacher.email}</TableCell>
                                    <TableCell>{teacher.contacts}</TableCell>
                                    <TableCell>{teacher.dateOfBirth}</TableCell>
                                    <TableCell>{teacher.joinDate}</TableCell>
                                    <TableCell>{teacher.age}</TableCell>
                                    <TableCell>
                                        <Button variant="contained" onClick={() => handleEdit(teacher)}>Edit</Button>
                                        <Button variant="contained" color="secondary" onClick={() => handleDelete(teacher.id)}>Delete</Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>

                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>{editMode ? 'Edit Teacher' : 'Add Teacher'}</DialogTitle>
                    <DialogContent>
                        <TextField
                            margin="dense"
                            label="Name"
                            name="teacherName"
                            value={currentTeacher?.teacherName || ''}
                            onChange={handleChange}
                            fullWidth
                        />
                        <TextField
                            margin="dense"
                            label="Password"
                            name="password"
                            value={currentTeacher?.password || ''}
                            onChange={handleChange}
                            fullWidth
                        />
                        <TextField
                            margin="dense"
                            label="Email"
                            name="email"
                            value={currentTeacher?.email || ''}
                            onChange={handleChange}
                            fullWidth
                        />
                        <TextField
                            margin="dense"
                            label="Contacts"
                            name="contacts"
                            value={currentTeacher?.contacts || null}
                            onChange={handleChange}
                            fullWidth
                        />
                        <TextField
                            margin="dense"
                            label="Date of Birth"
                            name="dateOfBirth"
                            value={currentTeacher?.dateOfBirth || ''}
                            onChange={handleChange}
                            fullWidth
                        />
                        <TextField
                            margin="dense"
                            label="Join Date"
                            name="joinDate"
                            value={currentTeacher?.joinDate || ''}
                            onChange={handleChange}
                            fullWidth
                        />
                        <TextField
                            margin="dense"
                            label="Age"
                            name="age"
                            value={currentTeacher?.age || 0}
                            onChange={handleChange}
                            fullWidth
                        />
                    </DialogContent>
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={handleSubmit} color="primary">
                            {editMode ? 'Update' : 'Add'}
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </main>
    );
};

export default AddTeacher;
