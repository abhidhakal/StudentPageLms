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

interface Transportation {
    busNo: number;
    busFee: number;
    route: string;
}

interface ClassSchool {
    className: string;
    fees: number;
    subId: any[];
}

interface Student {
    studentId: number;
    busNo: Transportation;
    studentName: string;
    password: string;
    email: string;
    contacts: number;
    rollNo: number;
    grade: ClassSchool;
    section: string;
    dateOfBirth: string;  // Changed to string for simplicity
    admissionDate: string;  // Changed to string for simplicity
    age: number;
}

const AddStudent: React.FC = () => {
    const [students, setStudents] = useState<Student[]>([]);
    const [filter, setFilter] = useState<string>('');

    useEffect(() => {
        const fetchStudents = async () => {
            try {
                const response = await axios.get('http://localhost:8080/students/get');
                console.log('API response data:', response.data);  // Log response data
                if (response.data && Array.isArray(response.data.data)) {
                    const transformedData: Student[] = response.data.data.map((student: any) => ({
                        studentId: student.studentId,
                        busNo: student.busNo,
                        studentName: student.studentName,
                        password: student.password,
                        email: student.email,
                        contacts: student.contacts,
                        rollNo: student.rollNo,
                        grade: student.grade,
                        section: student.section,
                        dateOfBirth: new Date(student.dateOfBirth).toLocaleDateString(),
                        admissionDate: new Date(student.admissionDate).toLocaleDateString(),
                        age: student.age,
                    }));
                    setStudents(transformedData);
                } else {
                    console.error('Expected an array in data property but got:', response.data);
                }
            } catch (error) {
                console.error('Error fetching students:', error);
            }
        };
        fetchStudents();
    }, []);

    const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFilter(event.target.value);
    };

    const filteredStudents = students.filter(
        (student) =>
            student.studentName.toLowerCase().includes(filter.toLowerCase()) ||
            student.section.toLowerCase().includes(filter.toLowerCase())
    );

    return (
        <main className="main-area">
            <div className="content">
                <div className="students-display">
                    <Button variant="contained" className="add-student-btn">
                        Add Student
                    </Button>
                    <Button variant="contained" className="edit-student-btn">
                        Edit Student
                    </Button>
                    <Button variant="contained" className="delete-student-btn">
                        Delete Student
                    </Button>
                </div>

                <TextField
                    label="Filter Students"
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
                                <TableCell>Section</TableCell>
                                <TableCell>Roll No</TableCell>
                                <TableCell>Date of Birth</TableCell>
                                <TableCell>Admission Date</TableCell>
                                <TableCell>Age</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {filteredStudents.map((student) => (
                                <TableRow key={student.studentId}>
                                    <TableCell>{student.studentId}</TableCell>
                                    <TableCell>{student.studentName}</TableCell>
                                    <TableCell>{student.password}</TableCell>
                                    <TableCell>{student.email}</TableCell>
                                    <TableCell>{student.contacts}</TableCell>
                                    <TableCell>{student.section}</TableCell>
                                    <TableCell>{student.rollNo}</TableCell>
                                    <TableCell>{student.dateOfBirth}</TableCell>
                                    <TableCell>{student.admissionDate}</TableCell>
                                    <TableCell>{student.age}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </main>
    );
};

export default AddStudent;
