import UserForm from '../../Components/Userform/UserForm';
import UserList from '../../Components/Userlist/UserList';
import './ManageUser.css'
const  ManageUser=()=>{
     return (
        <div className="ManageUser-container text-light">
            <div className="left-column">
               <UserForm/>
            </div>
            
             <div className="right-column">
                <UserList/>
            </div>
        </div>
    )

}
export default ManageUser;
