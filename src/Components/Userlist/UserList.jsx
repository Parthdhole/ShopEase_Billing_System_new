import toast from "react-hot-toast";
import { deleteUser } from "../../Service/UserService";
import { useState } from "react";


const UserList=({users,setUsers})=>{
   console.log("USERS RECEIVED IN UserList:", users);
  const [searchTerm, setSearchTerm] = useState('');
  const filteredUsers = users.filter(user => 
    // user.name.toLowerCase().includes(searchTerm.toLowerCase())
      (user.name || user.email||"").toLowerCase().includes(searchTerm.toLowerCase())
      


  ) ;
  const deleteByUserId= async(userId)=>{
    try{
      const response= await deleteUser(userId);
     setUsers(previousUsers=>previousUsers.filter(user=>user.userId!==userId));
     toast.success("User deleted successfully");
    }catch(error){
      console.error("Error deleting user",error);
      toast.error("Unable to delete user");

    }
  }
      return(
      <div className="category-list container" style={{ height: '100%', overflowX: 'auto', overflowY: 'hidden' }}>
      <div className="row pe-2">

        <div className='row pe-2'>
          <div className='input-group mb-3'>
            <input
              type="text"
              name="keyword"
              id="keyword"
              placeholder='Search by Keyword'
              className='form-control'
              onChange={(e) => setSearchTerm(e.target.value)}
              value={searchTerm}
            />
            <span className="input-group-text bg-warning">
              <i className="bi bi-search"></i>
            </span>
          </div>
        </div>
        <div className="row g-3 pe-2">

          {
            filteredUsers.map((user,index) => (
              <div key={index} className="col-12">
                <div className="card p-3 bg-dark">
                  <div className="d-flex align-items-center">
                    <div className="flex-grow-1">
                      <h5 className="mb-1 text-white">{user.name|| user.email}</h5>
                      <p className="mb-0 text-light"> {user.email}</p>
                    </div>
                    <div>
                      {/* <button className="btn btn-danger btn-sm" onClick={()=>deleteByUserId(user.userId)}>
                        <i className="bi bi-trash">

                        </i>
                      </button> */}
                      <button
  className="btn btn-danger btn-sm"
  onClick={() => {
    console.log("Clicked user object:", user);
    console.log("Possible IDs:", user.id, user.userId);
    deleteByUserId(user.id || user.userId);
  }}
>
  <i className="bi bi-trash"></i>
</button>

                    </div>

                  </div>
             </div>
        
         </div>
            ))
          }

        </div>



        
      </div>
    </div>
        
    )

}
export default UserList;