import './CategoryList.css';
import { useContext, useState } from "react";
import { AppContxt } from "../../Conntext/AppContext";
import { deletCategory } from '../../Service/CategoryService';
import { toast } from 'react-hot-toast';

const CategoryList = () => {

  const [searchTerm, setSearchTerm] = useState('');
  const { categories, setCategories } = useContext(AppContxt);

  const filteredCategories = categories.filter(category =>
    category.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const deletByCategoryID = async (catagoryId) => {
    try {
      const response = await deletCategory(catagoryId);
      console.log("RAW RESPONSE:", response);
      console.log("STATUS:", response.status);

      if (response.status === 200 || response.status === 204) {

        const updatedCategories = categories.filter(
          (category) => category.catagoryId !== catagoryId
        );

        setCategories(updatedCategories);
        toast.success("Category Deleted Successfully");

      } else {
        toast.error("Unable to delete successfully");
      }

    } catch (error) {
      console.error(error);
      toast.error("Unable to delete successfully");
    }
  };

  return (
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
          {filteredCategories.map((category, index) => (
            <div key={index} className="col-12">
              <div className="card p-3" style={{ backgroundColor: category.bgColor }}>
                <div className="d-flex align-items-center">

                  {/* ✅ IMAGE ADDED HERE */}
                  <div style={{ marginRight: '15px' }}>
                    <img
                      src={category.imgUrl}
                      alt={category.name}
                      className="category-image"
                      onError={(e) => (e.target.src = "https://via.placeholder.com/80")}
                    />
                  </div>

                  <div className="flex-grow-1">
                    <h5 className="mb-1 text-black">{category.name}</h5>
                    <p className="mb-0 text-black">5 Items</p>

                    <button
                      className="btn btn-danger btn-sm mt-2"
                      onClick={() => deletByCategoryID(category.catagoryId)}
                    >
                      <i className="bi bi-trash"></i>
                    </button>

                  </div>

                </div>
              </div>
            </div>
          ))}
        </div>

      </div>
    </div>
  );
};

export default CategoryList;
