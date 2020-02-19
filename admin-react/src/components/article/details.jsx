import React from 'react';

export default class ArticleDetails extends React.Component {

  // constructor(props, context) {
  //   super(props, context);
  // }

  render() {
    return <div>
        <h2 className="page-header">
        Article Details {this.props.match.params.id}
        </h2>
    </div>;
  }
}