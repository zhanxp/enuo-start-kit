const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const webpack = require('webpack');
const path = require('path');

function resolve(dir) {
    return path.resolve(__dirname, dir)
}

module.exports = {
    entry: {
        vendor: ['jquery', 'angular', '@uirouter/angularjs', 'babel-polyfill', 'bootstrap/dist/css/bootstrap.min.css', 'bootstrap/dist/js/bootstrap.min.js']
    },
    output: {
        path: resolve('dll'),
        filename: 'js/[name].[chunkhash:8].js',
        library: '[name]_library'
    },
    resolve: {
        extensions: ['.js', '.json'],
        alias: {
            'jquery$': 'jquery/dist/jquery.min.js'
        }
    },
    module: {
        rules: [{
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
                loader: 'url-loader?limit=10000&name=images/[name].[hash:8].[ext]'
            },
            {
                test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
                loader: 'url-loader?limit=10000&name=fonts/[name].[hash:8].[ext]'
            },
            {
                test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
                loader: 'url-loader?limit=10000&name=media/[name].[hash:8].[ext]'
            }
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            "window.jQuery": 'jquery',
            "window.$": 'jquery'
        }),
        new CleanWebpackPlugin(['dll']),
        new CopyWebpackPlugin([
            { from: 'src/externals' }
        ]),
        new webpack.DllPlugin({
            path: resolve("dll/[name]-manifest.json"),
            name: "[name]_library",
            context: __dirname
        }),
        new HtmlWebpackPlugin({
            filename: 'template.html',
            title: '<%= htmlWebpackPlugin.options.title %>',
            inject: 'head',
            chunks: ['vendor'],
            template: './src/template.html',
            minify: {
                removeComments: true,
                collapseWhitespace: false
            }
        })
    ]
};