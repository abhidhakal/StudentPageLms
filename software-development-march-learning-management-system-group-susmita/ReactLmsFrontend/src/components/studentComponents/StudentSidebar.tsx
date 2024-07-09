import '../styles/general.css';
import '../styles/sidebar.css';

interface SidebarProps {
    onSectionChange: (section: string) => void;
}

function Sidebar({ onSectionChange }: SidebarProps) {
    const handleItemClick = (section: string) => {
        onSectionChange(section);
    };

    return (
        <section className="sidebar">
            <div className="logo">
                <img className="logoo" src="/assets/images/logoo.png" alt="Schoolify" />
            </div>
            <div className="contents">
                <div className="sidebar_obj" onClick={() => handleItemClick('tasks')}>
                    <img src="/assets/icons/tasks-app-svgrepo-com.svg" alt=""/>
                    <p>Tasks</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('courses')}>
                    <img src="/assets/icons/learn-svgrepo-com.svg" alt=""/>
                    <p>Courses</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('result')}>
                    <img src="/assets/icons/a-best-test-result-svgrepo-com.svg" alt=""/>
                    <p>Results</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('attendance')}>
                    <img src="/assets/icons/add-square-svgrepo-com-2.svg"/>
                    <p>Attendance</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('student-fees')}>
                    <img src="/assets/icons/credit-card-payment-svgrepo-com.svg" alt=""/>
                    <p>Fees</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('feedback')}>
                    <img src="/assets/icons/feedback-svgrepo-com.svg" alt=""/>
                    <p>Feedback</p>
                </div>
            </div>
        </section>
    );
}

export default Sidebar;
