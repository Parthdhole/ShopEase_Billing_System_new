import { Route, Routes } from "react-router-dom";
import Menubar from "./Components/Menubar/Menubar";
import Dashboard from "./pages/DashBoard/Dashbord";
import ManageCategory from "./pages/ManageCategory/ManageCategory";
import ManageUser from "./pages/ManagUsers/ManageUser";
import ManageItems from "./pages/ManageItems/ManageItems";
import Explore from "./pages/Explore/Explore";
import { Toaster } from 'react-hot-toast';

const App=()=>{
  return(
      <div>
        <Menubar/>
        <Toaster/>
        <Routes>
          <Route path="/dashboard" element={<Dashboard/>}/>
          <Route path="/category" element={<ManageCategory/>}/>
          <Route path="/users" element={<ManageUser/>}/>
          <Route path="/explore" element={<Explore/>}/>
           <Route path="/items" element={<ManageItems/>}/>
           <Route path="/" element={<Dashboard/>}/>

        </Routes >
      </div>
  );
}

export default App;