import {Link} from "react-router-dom";

const Header = ()=>{
    return(
        <header className = "header">
            <div className = "logo">EMS</div>
            <nav className = "nav-links">
                <Link to = "/employee">Employees</Link>
                <Link to="/me">My Profile</Link>
            </nav>
        </header>
    )
}

export default Header;