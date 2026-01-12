import { AppContxt } from "../../Conntext/AppContext";
import { deletItem } from '../../Service/ItemService';
import { useContext, useState } from "react";
import toast from "react-hot-toast";
import './ItemList.css';


const ItemList = () =>{
    console.log("ItemList component rendered");
    const{itemsData,setItemsData}=useContext(AppContxt);
    console.log("Context Value:", {itemsData});
    
    const[searchTerm, setSearchTerm]= useState('');
    const filterItems=itemsData.filter(item=>{
         console.log("Checking item:", item);
     return (item?.name ?? "")
         .toLowerCase()
    .includes(searchTerm.toLowerCase());
    })
    const removeItem= async(itemId)=>{
        try{
         const response=  await deletItem(itemId);
            if(response.status===200 || response.status===204){
            const updatedItems= itemsData.filter((item)=>item.itemId!==itemId);
            setItemsData (updatedItems);
            toast.success("Item Deleted Successfully");
            }else{
                toast.error("Unable to delete item");
            }

        }catch(error){
            console.error("Error deleting item",error);
            toast.error("Unable to delete item");
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
            {filterItems.map((item)=>
             (
                <div className="col-12" key={item.itemId}>
                   <div className="card p-3 bg-dark">
                    <div className="d-flex align-items-center">
                        <div style={{marginRight:'10px'}}>
                            <img src={item.imgUrl} 
                            alt={item.name} 
                            className="item-image"
                            />
                            <div className="flex-grow-1">
                                <h6 className="mb-1 text-white">
                                    {item.name}</h6>
                                <p className="mb-0 text-white">
                                    Category: {item.categoryName} 
                                </p>
                                <span className="mb-0 text-block badge rounded-pill bg-warning">

                                     &#8377; {item.price}
                                 </span>
                            </div>
                            <div>
                                <button className="btn btn-danger btn-sm" onClick={()=>removeItem(item.itemId)}>
                                    <i className="bi bi-trash"></i>
                                </button>

                            </div>
                            
                        </div>
                    </div>
                   </div>
                </div>
            )


            )}
        
          
        </div>

      </div>
    </div>    )
}
export default ItemList;