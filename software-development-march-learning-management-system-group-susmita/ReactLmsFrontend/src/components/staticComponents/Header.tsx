import '../styles/header.css';

interface SidebarProps {
    onSectionChange: (section: string) => void;
}

function Header({ onSectionChange }: SidebarProps) {
    const handleItemClick = (section: string) => {
        onSectionChange(section);
    };

    return (
        <header className="header">
            <div className="left-area">
                <div className="header_obj" onClick={() => handleItemClick('go-main')}>
                    <p>DashBoard</p>
                </div>
                <div className="header_obj" onClick={() => handleItemClick('notice')}>
                    <p>Notices</p>
                </div>
                <div className="header_obj" onClick={() => handleItemClick('requests')}>
                    <p>Request</p>
                </div>
            </div>

            <div className="right-area">
                <div className="notif-icon">
                    <img src={"/assets/icons/notif-bell-icon-white.svg"}/>
                </div>

                <div className="profile-icon">
                    <img className="profile" src="/assets/icons/profile-circle-icon-white.svg"/>
                    <p>Abhinav Dhakal</p>
                    <img className="arrow-icon" src="/assets/icons/arrow-white.svg" alt=""/>
                </div>
            </div>
        </header>
    )
}

export default Header;