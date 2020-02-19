import React from 'react';
import { pageListAction } from '../../service/articleService'
import { Link } from 'react-router-dom';
import Moment from 'react-moment';
import Pager from '../shared/pager';

export default class Article extends React.Component {

  // constructor(props, context) {
  //   super(props, context);
  // }

  componentWillMount = () => {
    this.loadData(1,10);
  }

  loadData = (pageIndex, pageSize) => {
    this.setState({
      pageIndex:pageIndex,
      pageSize: pageSize,
      items:[],
      total:0
    });

    var self = this;
    pageListAction(pageIndex, pageSize)
    .then(function (response) {
        var res = response.data;
        console.log(response.data);
        if(res.success){
           var newState = {};
           newState.pageIndex = res.data.pageIndex;
           newState.total = res.data.total;
           newState.items = res.data.items;
           self.setState(newState);
        }
    })
    .catch(function (error) {
        console.log(error);
    });
  }

  goPage = (pageIndex,event) => {
    this.loadData(pageIndex, 10);
  }

  render() {

    var items = this.state.items || [];
    var {total} = this.state;
    var {pageIndex} = this.state;
    var {pageSize} = this.state;

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
                <Link to={"/article/edit/" + items[i].id } >eidt</Link>
                 &nbsp;|&nbsp;  
                <Link to={"/article/details/" + items[i].id }>details</Link>
              </td>
      </tr>);
    }

    return <div>
              <div>
                <h2 className="page-header">
                Article
                <small>
                  <Link to="/article/add" >create</Link>
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
            <Pager total={total} pageIndex={pageIndex} pageSize={pageSize} go={this.goPage}></Pager>
          </div>;
  }
}