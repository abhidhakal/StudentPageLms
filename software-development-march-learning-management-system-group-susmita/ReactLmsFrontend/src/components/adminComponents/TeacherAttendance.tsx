import React, { useEffect, useState } from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Button,
    Checkbox,
} from '@mui/material';
import '../styles/main.css';
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
    attendance: boolean[];  // Added for attendance status for five days
}

const AttendanceTable: React.FC = () => {
    const [teachers, setTeachers] = useState<Teacher[]>([]);

    useEffect(() => {
        const fetchTeachers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/teachers/get');
                console.log('API response data:', response.data);  // Log response data
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
                        attendance: [false, false, false, false, false],  // Initialize attendance for five days
                    }));
                    setTeachers(transformedData);
                } else {
                    console.error('Expected an array in data property but got:', response.data);
                }
            } catch (error) {
                console.error('Error fetching teachers:', error);
            }
        };
        fetchTeachers();
    }, []);

    const handleAttendanceChange = (teacherId: number, dayIndex: number) => {
        setTeachers((prevTeachers) =>
            prevTeachers.map((teacher) =>
                teacher.id === teacherId
                    ? {
                        ...teacher,
                        attendance: teacher.attendance.map((status, index) =>
                            index === dayIndex ? !status : status
                        ),
                    }
                    : teacher
            )
        );
    };

    const handleSaveAttendance = async () => {
        const attendanceData = teachers.map((teacher) => ({
            id: teacher.id,
            attendance: teacher.attendance,
        }));
        try {
            await axios.post('http://localhost:8080/teachers/attendance', attendanceData);
            console.log('Attendance saved successfully');
        } catch (error) {
            console.error('Error saving attendance:', error);
        }
    };

    return (
        <main className="main-area">
            <div className="content">
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Serial No.</TableCell>
                                <TableCell>Teacher Name</TableCell>
                                <TableCell>Day 1</TableCell>
                                <TableCell>Day 2</TableCell>
                                <TableCell>Day 3</TableCell>
                                <TableCell>Day 4</TableCell>
                                <TableCell>Day 5</TableCell>
                                <TableCell>Save Status</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {teachers.map((teacher, index) => (
                                <TableRow key={teacher.id}>
                                    <TableCell>{index + 1}</TableCell>
                                    <TableCell>{teacher.teacherName}</TableCell>
                                    {teacher.attendance.map((status, dayIndex) => (
                                        <TableCell key={dayIndex}>
                                            <Checkbox
                                                checked={status}
                                                onChange={() => handleAttendanceChange(teacher.id, dayIndex)}
                                            />
                                        </TableCell>
                                    ))}
                                    <TableCell>
                                        <Checkbox />
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={handleSaveAttendance}
                    style={{ marginTop: '20px' }}
                >
                    Save Attendance
                </Button>
            </div>
        </main>
    );
};

export default AttendanceTable;
