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
                <div className="sidebar_obj" onClick={() => handleItemClick('add-student')}>
                    <img src="/assets/icons/add-square-svgrepo-com-2.svg" alt="" />
                    <p>Student</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('add-teacher')}>
                    <img src="/assets/icons/add-square-svgrepo-com-2.svg" alt="" />
                    <p>Teacher</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('ledger')}>
                    <img src="/assets/icons/invoice-ledger-line-svgrepo-com.svg" alt="" />
                    <p>Ledger</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('teacher-attendance')}>
                    <img src="/assets/icons/add-square-svgrepo-com-2.svg" />
                    <p>Teacher Attendance</p>
                </div>
                <div className="sidebar_obj" onClick={() => handleItemClick('handle-fees')}>
                    <img src="/assets/icons/credit-card-payment-svgrepo-com.svg" alt="" />
                    <p>Handle Fees</p>
                </div>
            </div>
        </section>
    );
}

export default Sidebar;
