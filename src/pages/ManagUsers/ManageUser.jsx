import { useEffect ,useState } from 'react';
import UserForm from '../../Components/Userform/UserForm';
import UserList from '../../Components/Userlist/UserList';
import { fetchUsers } from "../../Service/UserService";

import './ManageUser.css'
import toast from 'react-hot-toast';
const  ManageUser=()=>{
    const [users,setUsers]=useState([]);
    const[Loading,setLoading]=useState(false);
      useEffect(() => {
        
          async function loadUsers(){
                try{
                    setLoading(true);
                    const response=await fetchUsers();
                     console.log("FULL RESPONSE FROM BACKEND:", response); // Debug: see full response
        console.log("RESPONSE DATA:", response.data);
        if (Array.isArray(response.data)) {      
                    setUsers(response.data);
                     console.log("USERS SET IN STATE:", response.data);
        }else {
          console.warn("Response data is not an array!", response.data);
          setUsers([]);
        }


                }catch(error){
                     console.error("ERROR FETCHING USERS:", error); 
                    toast.error("Unable in fetch users");
                }
                finally{
                    setLoading(false);
                }
            }
            loadUsers();
          
    
      },[])
     return (
        <div className="ManageUser-container text-light">
            <div className="left-column">
               <UserForm SetUser={setUsers} />
            </div>
            
             <div className="right-column">
                <UserList users={users} setUsers={setUsers} />
            </div>
        </div>
    )

}
export default ManageUser;
