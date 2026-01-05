import toast from 'react-hot-toast';
import './Login.css';
import { AppContxt} from "../../Conntext/AppContext";
import { login } from '../../Service/AuthService';
import { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
const Login = () => {
    const {setAuthdata}=useContext(AppContxt);
    const navigate=useNavigate();
    const[loading,setLoading]=useState(false);
    const[data,setData]=useState({
        email:"",
        password:""
    });
    const onchangeHandler=(e)=>{
        const name=e.target.name;
        const value=e.target.value;
        setData((data)=>({
            ...data,
            [name]:value}));
             
    }
  const  onSubmitHandler=async(e)=>{
        e.preventDefault();
        setLoading(true);
        try{
            //api call for login
           const response = await login(data);
           if(response.status === 200){
            toast.success("Login successful");
            localStorage.setItem("token",response.data.token);
            localStorage.setItem("role",(response.data.role));
            setAuthdata(response.data.token,response.data.role);
            navigate('/dashboard');
           }
           
        }catch(error){
            console.error("Login failed",error);
            toast.error("Email or password is incorrect");
        }
        finally{
            setLoading(false);
        }
    }
            
  return (
    <div className="bg-light login-container d-flex justify-content-center align-items-center vh-100 login-background">
     <div className="card shadow-lg w-100" style={{maxWidth: '480px'}}>
        <div className="card-body">
            <div className="text-center">
                <h1 className='card-title '>Sign in</h1>
                <h1 className='card-text text-muted'>
                    Sign in below to access your account
                    </h1>

            </div>
            <div className="mt-4">
                <form onSubmit={onSubmitHandler}>
                    <div className="mb-4">
                        <label htmlFor="email" className="form-label text-muted"  >
                            Email address
                        </label>
                        <input type="text" name="email" id="email" placeholder="Yourname@ex.com" className='form-control' onChange={onchangeHandler}value={data.email}/>
                    </div>
                    <div className="mb-4">
                        <label htmlFor="password" className="form-label text-muted">
                            Password
                        </label>
                        <input type="password" name="password" id="password" placeholder="******" className='form-control' onChange={onchangeHandler}value={data.password}/>
                    </div>
                    <div className="d-grid">
                        <button type="submit" className="btn btn-dark btn-lg">
                            Sign in
                        </button>
                    </div>
                </form>
            </div>
        </div>
     </div>
    </div>
  );
       
}
export default Login;