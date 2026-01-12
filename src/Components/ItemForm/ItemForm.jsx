import { useState ,useContext } from "react";
import { assets } from "../../assets/assets";
import { AppContxt } from "../../Conntext/AppContext";
import toast from "react-hot-toast";
import { addItem } from "../../Service/ItemService";


const ItemForm = () => {
    const {categories,setItemsData,itemsData,setCatrgories}=useContext(AppContxt);
    const [image , setImage] = useState(false);
    const [loading,setLoading]=useState(false);
    const [data,setData]=useState({
      name:"",
      categoryId:"",
      price:"",
      description:"",
      });
      const onChangeHandler = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setData((data) => ({
          ...data,
          [name]: value,
        }));
      }
      const onSubmitHandler = async(e) => {
        e.preventDefault();
        setLoading(true);
        const fromData= new FormData();
        fromData.append("item",JSON.stringify(data));
        fromData.append("file",image);
        try{
         if(!image){
          toast.error("Select image");
        

        }
        console.log("Submitting item:", data, "image:", image);
        const response= await addItem(fromData);
        if(response.status===201){
        toast.success("Item added successfully"); 
          setItemsData([...itemsData,response.data]);
         
          setCatrgories((prevCategories)=>prevCategories.map((category)=>category.categoryId===data.categoryId?{...category,items:category.items+1}:category)); 
          
          setData({
            name:"",
            categoryId:"",
            price:"",
            description:"",
          });
          setImage(false);
        
        }  else{
          toast.error("unable to add item");
            
          }
      }catch(error){
        console.error("unable while adding item",error);
        
        

      }finally{
        setLoading(false);
      }

      }
 return (
    <div
      className="item-form-container"
      style={{ height: "100vh", overflow: "auto", overflowX: "hidden" }}
    >
      <div className="mx-2 mt-2">
        <div className="row">
          <div className="card col-md-8 form-container">
            <div className="card-body">
              <form onSubmit={onSubmitHandler}>
              {/* Image Upload */}
              <div className="mb-3">
                <label htmlFor="image" className="form-label">
                  <img
                    // src="https://placehold.co/48x48"
                    src={image ? URL.createObjectURL(image) :assets.upload }alt="" width={48}
                  />
                </label>
                <input
                  type="file"
                  name="image"
                  id="image"
                  className="form-control"
                  hidden onChange={(e) => setImage(e.target.files[0 ])}
                />
              </div>
              

              {/* Item Name */}
              <div className="mb-3">
                <label htmlFor="name" className="form-label">Name</label>
                <input
                  type="text"
                  name="name"
                  id="name"
                  className="form-control"
                  placeholder="Item Name"
                  onChange={onChangeHandler}
                  value={data.name}
                />
              </div>

              {/* Description */}
              {/* <div className="mb-3">
                <label htmlFor="description" className="form-label">Description</label>
                <textarea
                  rows="5"
                  name="description"
                  id="description"
                  className="form-control"
                  placeholder="Write content here"
                ></textarea>
              </div> */}

              {/* Category */}
              <div className="mb-3">
                <label htmlFor="category" className="form-label">Category</label>
                <select
                  name="categoryId"
                  id="category"
                  className="form-control"
                  onChange={onChangeHandler}
                  value={data.categoryId}
                >
                  <option value="">Select Category</option>
                 {categories.map((category) => (
                  <option key={category.categoryId} value={category.categoryId}>{category.name}</option>
                ))}
                </select>
              </div>

              {/* Price */}
              <div className="mb-3">
                <label htmlFor="price" className="form-label">Price</label>
                <input
                  type="number"
                  name="price"
                  id="price"
                  className="form-control"
                  placeholder="₹200.00" 
                  onChange={onChangeHandler}
                  value={data.price}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="description" className="form-label">
                  description
                </label>
                <textarea rows="5" name="description" id="description" className="form-control" placeholder="Write content here..." onChange={onChangeHandler} value={data.description}></textarea>
              </div>
              <button type="submit" className="btn btn-primary w-100" > 
             {loading ? "Loading..." : "Save"}
              </button>
                </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ItemForm;
