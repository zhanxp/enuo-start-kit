const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const webpack = require('webpack');
const path = require('path');
require("babel-polyfill");

function resolve(dir) {
    return path.resolve(__dirname, dir)
}

var config = {
    entry: {
        index: ['babel-polyfill', './src/index.js']
    },
    devtool: 'inline-source-map',
    output: {
        path: resolve('dist'),
        chunkFilename: 'js/[name].[chunkhash:8].js',
        filename: 'js/[name].[chunkhash:8].js'
    },
    externals: {},
    resolve: {
        extensions: ['.js', '.json'],
        alias: {
            'jquery$': 'jquery/dist/jquery.min.js',
            'src': resolve('src'),
            'assets': resolve('src/assets'),
            'components': resolve('src/components')
        }
    },
    module: {
        rules: [{
                test: /\.js$ /,
                use: [{
                        loader: 'ng-annotate-loader'
                    },
                    {
                        loader: 'babel-loader'
                    }
                ],
                include: [resolve('src/js')]
            }, {
                test: /\.html$/,
                loader: 'html-loader'
            }, {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.less$/,
                loader: "less-loader"
            },
            {
                test: /\.scss$/,
                use: [
                    "style-loader", // creates style nodes from JS strings
                    "css-loader", // translates CSS into CommonJS
                    "sass-loader" // compiles Sass to CSS
                ]
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
                test: /\.(swf|mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
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
        new webpack.DllReferencePlugin({
            context: __dirname,
            manifest: require('./dll/vendor-manifest.json')
        }),
        new CopyWebpackPlugin([
            { from: 'dll', ignore: ['template.html', 'vendor-manifest.json'] }
        ]),
        new CleanWebpackPlugin(['dist']),
        new webpack.DefinePlugin({
            PROD: process.env.NODE_ENV === 'production'
        }),
        new HtmlWebpackPlugin({
            filename: 'index.html',
            title: 'EnuoCMS',
            inject: true,
            chunks: ['index'],
            template: './dll/template.html',
            minify: {
                removeComments: true,
                collapseWhitespace: false
            }
        })
    ]
};

if (process.env.NODE_ENV === 'production') {
    config.plugins.push(new webpack.optimize.UglifyJsPlugin({
        mangle: {
            mangle: false
        }
    }));
}

module.exports = config;