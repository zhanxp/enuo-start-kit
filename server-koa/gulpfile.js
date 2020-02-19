'use strict';

var gulp = require('gulp');

gulp.task('bootstrap', function() {
    return gulp.src("bower_components/bootstrap/dist/**/*")
        .pipe(gulp.dest("public/bootstrap/"));
});

gulp.task('jquery', function() {
    return gulp.src("bower_components/jquery/dist/**/*")
        .pipe(gulp.dest("public/jquery/"));
});

gulp.task('default', ['bootstrap', 'jquery'], function() {

});