import Sidebar from '../components/staticComponents/Sidebar.tsx';
import '../components/styles/general.css';
import {useState} from "react";
import AddStudent from "../components/adminComponents/AddStudent.tsx";
import AddTeacher from "../components/adminComponents/AddTeacher.tsx";
import Ledger from "../components/adminComponents/Ledger.tsx";
import HandleFees from "../components/adminComponents/HandleFees.tsx";
import Header from "../components/staticComponents/Header.tsx";
import Head from "../components/staticComponents/Head.tsx";
import Main from "../components/staticComponents/Main.tsx";
import Notices from "../components/staticComponents/Notices.tsx";
import Requests from "../components/staticComponents/Requests.tsx";
import AttendanceTable from "../components/adminComponents/TeacherAttendance.tsx";

function AdminScreen() {
    const [currentSection, setCurrentSection] = useState('');

    const handleSectionChange = (section: string) => {
        setCurrentSection(section);
    };

    let content;
    switch (currentSection) {
        case 'go-main':
            content = <Main/>;
            break;
        case 'notice':
            content = <Notices/>;
            break;
        case 'requests':
            content = <Requests/>;
            break;
        case 'add-student':
            content = <AddStudent />;
            break;
        case 'add-teacher':
            content = <AddTeacher />;
            break;
        case 'ledger':
            content = <Ledger />;
            break;
        case 'teacher-attendance':
            content = <AttendanceTable />;
            break;
        case 'handle-fees':
            content = <HandleFees />;
            break;
        default:
            content = <div><Main/></div>;
    }

    return (
        <div className="admin-screen">
            <Head/>
            <Header onSectionChange={handleSectionChange}/>
            <Sidebar onSectionChange={handleSectionChange} />
            <main className="main">
                {content}
            </main>
        </div>
    );
}

export default AdminScreen;
