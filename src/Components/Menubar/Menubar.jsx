import { assets } from '../../assets/assets';
import './Menubar.css';
import { Link ,useNavigate } from "react-router-dom";
import { useContext } from "react";
import { AppContxt } from "../../Conntext/AppContext";

const Menubar =()=>{
    const navigate=useNavigate();
    const{setAuthdata}=useContext(AppContxt);
    const logout = ()=>{
        localStorage.removeItem("token");
        localStorage.removeItem("role");
        setAuthdata(null,null);
        navigate('/login');
    }
    return (
     <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-2">
    <a className="navbar-brand" href="#">
        <img src={assets.logo} alt="Logo" height="90"/>
    </a>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <div className="collapse navbar-collapse p-2" id="navbarNav">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
                <Link className="nav-link" to="/dashboard">Dashbord</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/explore">Explore</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/items">Manage Item</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/category">Manage Categories</Link>
            </li>
              <li className="nav-item">
                <Link className="nav-link" to="/users">Manage Users</Link>
            </li>
        </ul>
     
         <ul className="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
          <li className="nav-item dropdown">
  <a
    href="#"
    className="nav-link dropdown-toggle"
    id="navbarDropdown"
    role="button"
    data-bs-toggle="dropdown"
    aria-expanded="false"
  >
    <img
      src={assets.Profile}
      width={32}
      height={32}
      className="rounded-circle"
      alt="profile"
    />
  </a>

  <ul
    className="dropdown-menu dropdown-menu-end"
    aria-labelledby="navbarDropdown"
  >
    <li>
      <a href="#!" className="dropdown-item">
        Settings
      </a>
    </li>

    <li>
      <a href="#!" className="dropdown-item">
        Activity log
      </a>
    </li>

    <li>
      <hr className="dropdown-divider" />
    </li>

    <li>
      <a
        href="#!"
        className="dropdown-item text-danger"
        onClick={logout}
      >
        Logout
      </a>
     </li>
    </ul>
   </li>
    </ul>
    </div>
</nav>
    );
  

}
export default Menubar;