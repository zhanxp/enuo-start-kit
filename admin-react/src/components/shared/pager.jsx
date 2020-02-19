import React from 'react';

export default class Pager extends React.Component {
    // constructor(arg) {
    //     super(arg);
    // }

    // componentWillMount = ()=> {

    // }

    render() {
        const total = this.props.total;
        const pageSize = this.props.pageSize;
        const pageIndex = this.props.pageIndex;
        const url = this.props.url || '?';
        var pageCount = parseInt((total + pageSize - 1) / pageSize);

        var prevIndex = <li className="prev disabled"><a>首页</a></li>;
        var prev = <li className="prev disabled"><a>&nbsp;&lt;</a></li>;
        if (pageIndex > 1){
            prevIndex = <li className="prev"><a onClick={this.props.go.bind(this,1)} data-page="1" title="首页">首页</a></li>;
            prev = <li className="prev"><a onClick={this.props.go.bind(this, pageIndex - 1)} data-page={pageIndex - 1} title="上一页">&nbsp;&lt;</a></li>;
        }

        var prevNo = [];
        var start = 1;
        if (pageIndex>4){
            start = pageIndex - 1;
            prevNo.push(<li><a onClick={this.props.go.bind(this, 1)}  data-page="1">1</a></li>);
            prevNo.push(<li><a onClick={this.props.go.bind(this, 2)} data-page="2">2</a></li>);
            prevNo.push(<li className="disabled"><a>&hellip;</a></li>);
        }

        var end = pageIndex + 1 > pageCount ? pageCount : pageIndex + 1;
        for(var i=1;i<=end;i++){
            if (pageIndex==i){
                prevNo.push(<li key={i} className="active"><a>{i}</a></li>);
            }else{
                prevNo.push(<li key={i} ><a onClick={this.props.go.bind(this, i)} data-page={i}>{i}</a></li>);
            }
        }

        if (end < pageCount - 2){
            prevNo.push(<li className="disabled "><a>&hellip;</a></li>);
        }

        if (end < pageCount - 1){
            prevNo.push(<li><a onClick={this.props.go.bind(this, pageCount - 1)} data-page={pageCount - 1 }>{pageCount - 1}</a></li>);
        }

        if (end < pageCount) {
            prevNo.push(<li><a onClick={this.props.go.bind(this, pageCount)} data-page={pageCount} >{pageCount}</a></li>);
        }

        var next = <li className="next disabled "><a>&gt;&nbsp;</a></li>;
        var nextIndex = <li className="next disabled "><a>末页</a></li>;
        if (pageIndex < pageCount){
            next = <li className="next "><a onClick={this.props.go.bind(this, pageIndex + 1)}  data-page={pageIndex + 1} title="下一页 ">&gt;&nbsp;</a></li>;
            nextIndex = <li className="next "><a onClick={this.props.go.bind(this, pageCount)} data-page={pageCount } title="末页 ">末页</a></li>;
        }

        return (
            <div className="page-mod">
                <div className="result">
                    共{total}条数据,分{pageCount}页,每页显示{pageSize}条
                </div>
                <nav className="text-right">
                    <ul className="pagination">
                        {prevIndex}
                        {prev}
                        {prevNo}
                        {next}
                        {nextIndex}
                    </ul>
                </nav>
            </div>
        )
    }
}