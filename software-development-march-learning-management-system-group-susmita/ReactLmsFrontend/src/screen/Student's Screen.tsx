import '../components/styles/general.css';
import {useState} from "react";
import Header from "../components/staticComponents/Header.tsx";
import Head from "../components/staticComponents/Head.tsx";
import Main from "../components/staticComponents/Main.tsx";
import Notices from "../components/staticComponents/Notices.tsx";
import Requests from "../components/staticComponents/Requests.tsx";
import StudentTasks from "../components/studentComponents/StudentTasks.tsx";
import StudentCourses from "../components/studentComponents/StudentCourses.tsx";
import StudentResult from "../components/studentComponents/StudentResult.tsx";
import StudentAttendance from "../components/studentComponents/StudentAttendance.tsx";
import StudentFees from "../components/studentComponents/StudentFees.tsx";
import Feedback from "../components/staticComponents/Feedback.tsx";
import StudentSidebar from "../components/studentComponents/StudentSidebar.tsx";

function StudentScreen() {
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
        case 'tasks':
            content = <StudentTasks/>;
            break;
        case 'courses':
            content = <StudentCourses/>;
            break;
        case 'result':
            content = <StudentResult/>;
            break;
        case 'attendance':
            content = <StudentAttendance/>;
            break;
        case 'student-fees':
            content = <StudentFees/>;
            break;
        case 'feedback':
            content = <Feedback/>;
            break;
        default:
            content = <div><Main/></div>;
    }

    return (
        <div className="admin-screen">
            <Head/>
            <Header onSectionChange={handleSectionChange}/>
            <StudentSidebar onSectionChange={handleSectionChange} />
            <main className="main">
                {content}
            </main>
        </div>
    );
}

export default StudentScreen;
