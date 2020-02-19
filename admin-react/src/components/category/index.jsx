import React from 'react';
import { listAction } from '../../service/categoryService'
import { Link } from 'react-router-dom';
import Moment from 'react-moment';

export default class Category extends React.Component {

  // constructor(props, context) {
  //   super(props, context);
  // }

  
  componentWillMount = () => {
    this.loadData();
  }

  loadData = () => {
    this.setState({
      items:[]
    });
    
    var self = this;
    listAction()
    .then(function (response) {
        var res = response.data;
        console.log(response.data);
        if(res.success){
           var newState = {};
           newState.items = res.data;
           self.setState(newState);
        }
    })
    .catch(function (error) {
        console.log(error);
    });
  }

  render() {

    var items = this.state.items || [];

    var rows = [];
    for(var i=0;i<items.length;i++){
      rows.push(<tr key={items[i].id}>
              <td>{items[i].id}</td>
              <td>{items[i].title}</td>
              <td>{items[i].ent_types}</td>
              <td>{items[i].ent_status}</td>
              <td>{items[i].creater}</td>
              <td>{items[i].updater}</td>
              <td><Moment format="YYYY/MM/DD">{items[i].create_date}</Moment></td>
              <td><Moment format="YYYY/MM/DD">{items[i].update_date}</Moment></td>
              <td>
                <Link to={"/category/edit/" + items[i].id } >eidt</Link>
                 &nbsp;|&nbsp;  
                <Link to={"/category/details/" + items[i].id }>details</Link>
              </td>
      </tr>);
    }

    return <div>
              <div>
                <h2 className="page-header">
                Category
                <small>
                  <Link to="/category/add" >create</Link>
                </small>
                </h2>
            </div>
            <table className='table table-bordered table-striped'>
              <thead>
                <tr>
                  <th>id</th>
                  <th>title</th>
                  <th>ent_types</th>
                  <th>ent_status</th>
                  <th>creater</th>
                  <th>updater</th>
                  <th>create_date</th>
                  <th>update_date</th>
                  <th>
                  </th>
                </tr>
                </thead>
                <tbody>
                  {rows}
                </tbody>
            </table>
          </div>;
  }
}