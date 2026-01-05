import UserList from "../Userlist/UserList";

const UserForm=()=>{
    return(
        <div>
  <div className="mx-2 mt-2">
      <div className="row">
        <div className="card col-md-8 form-container">
          <div className="card-body">
            {/* Category Name */}
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Name</label>
              <input
                type="text"
                name="name"
                id="name"
                className="form-control"
                placeholder="Jhon doe"
              />
            </div>
              <div className="mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input
                type="email"
                name="email"
                id="email"
                className="form-control"
                placeholder="Yourname@examole.com"
              />
            </div>
            </div>
              <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input
                type="password"
                name="password"
                id="password"
                className="form-control"
                placeholder="************"
              />
            </div>
           
            <div className="mb-3">
                <label htmlFor="bgcolor" className="form-label">Background color</label>
                <br/>
                <input type="color" 
                name="bgcolor" 
                id="bgcolor"
                 placeholder="#ffff"/>
            </div>
            <button type="submit" className="btn btn-warning w-100">Save</button>

          </div>
        </div>
      </div>
    </div>
    )
}
export default UserForm;