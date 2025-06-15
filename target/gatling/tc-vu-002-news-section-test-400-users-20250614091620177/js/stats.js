var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "400",
        "ok": "397",
        "ko": "3"
    },
    "minResponseTime": {
        "total": "353",
        "ok": "353",
        "ko": "3674"
    },
    "maxResponseTime": {
        "total": "4159",
        "ok": "2820",
        "ko": "4159"
    },
    "meanResponseTime": {
        "total": "820",
        "ok": "797",
        "ko": "3870"
    },
    "standardDeviation": {
        "total": "450",
        "ok": "364",
        "ko": "208"
    },
    "percentiles1": {
        "total": "687",
        "ok": "685",
        "ko": "3778"
    },
    "percentiles2": {
        "total": "937",
        "ok": "929",
        "ko": "4159"
    },
    "percentiles3": {
        "total": "1616",
        "ok": "1556",
        "ko": "4159"
    },
    "percentiles4": {
        "total": "2820",
        "ok": "2300",
        "ko": "4159"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 252,
    "percentage": 63.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 98,
    "percentage": 24.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 47,
    "percentage": 11.75
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3,
    "percentage": 0.75
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.22",
        "ok": "2.21",
        "ko": "0.02"
    }
},
contents: {
"req_access-news-sec-629658548": {
        type: "REQUEST",
        name: "Access News Section",
path: "Access News Section",
pathFormatted: "req_access-news-sec-629658548",
stats: {
    "name": "Access News Section",
    "numberOfRequests": {
        "total": "400",
        "ok": "397",
        "ko": "3"
    },
    "minResponseTime": {
        "total": "353",
        "ok": "353",
        "ko": "3674"
    },
    "maxResponseTime": {
        "total": "4159",
        "ok": "2820",
        "ko": "4159"
    },
    "meanResponseTime": {
        "total": "820",
        "ok": "797",
        "ko": "3870"
    },
    "standardDeviation": {
        "total": "450",
        "ok": "364",
        "ko": "208"
    },
    "percentiles1": {
        "total": "687",
        "ok": "685",
        "ko": "3778"
    },
    "percentiles2": {
        "total": "937",
        "ok": "929",
        "ko": "4159"
    },
    "percentiles3": {
        "total": "1616",
        "ok": "1556",
        "ko": "4159"
    },
    "percentiles4": {
        "total": "2820",
        "ok": "2300",
        "ko": "4159"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 252,
    "percentage": 63.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 98,
    "percentage": 24.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 47,
    "percentage": 11.75
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3,
    "percentage": 0.75
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.22",
        "ok": "2.21",
        "ko": "0.02"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
