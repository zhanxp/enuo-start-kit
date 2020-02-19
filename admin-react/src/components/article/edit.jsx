import React from 'react';

export default class ArticleEdit extends React.Component {

  render() {
    return <div>
        <h2 className="page-header">
        Edit Article {this.props.match.params.id}
        </h2>
    </div>;
  }
}