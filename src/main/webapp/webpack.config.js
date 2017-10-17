'use string'

module.exports = {
    context: __dirname + '/js',
    entry: './app.js',
    output: {
        filename: './js/bundle.js',
        library: "department"
    },
    watch: true,
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel-loader',
                query: {
                    presets: ['es2015']
                }
            }
        ]
    }

}
