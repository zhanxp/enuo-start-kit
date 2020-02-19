import React from 'react';

export default class About extends React.Component {

  // constructor(props, context) {
  //   super(props, context);
  // }

  render() {
    return <div>
        <h2 className="page-header">
            About
        </h2>
        <p>Use this area to provide additional information.</p>
        <address>
            <strong>Support:</strong>
            <a href="mailto:zhanxp@me.com">zhanxp@me.com</a>
            <br />
        </address>
    </div>;
  }
}