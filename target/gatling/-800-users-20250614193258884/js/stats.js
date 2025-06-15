var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "800",
        "ok": "782",
        "ko": "18"
    },
    "minResponseTime": {
        "total": "260",
        "ok": "260",
        "ko": "3033"
    },
    "maxResponseTime": {
        "total": "4822",
        "ok": "2973",
        "ko": "4822"
    },
    "meanResponseTime": {
        "total": "834",
        "ok": "766",
        "ko": "3792"
    },
    "standardDeviation": {
        "total": "764",
        "ok": "621",
        "ko": "524"
    },
    "percentiles1": {
        "total": "463",
        "ok": "450",
        "ko": "3700"
    },
    "percentiles2": {
        "total": "1161",
        "ok": "1078",
        "ko": "4108"
    },
    "percentiles3": {
        "total": "2473",
        "ok": "2073",
        "ko": "4822"
    },
    "percentiles4": {
        "total": "3744",
        "ok": "2733",
        "ko": "4822"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 535,
    "percentage": 66.875
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 70,
    "percentage": 8.75
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 177,
    "percentage": 22.125
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 18,
    "percentage": 2.25
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.67",
        "ok": "2.61",
        "ko": "0.06"
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
        "total": "800",
        "ok": "782",
        "ko": "18"
    },
    "minResponseTime": {
        "total": "260",
        "ok": "260",
        "ko": "3033"
    },
    "maxResponseTime": {
        "total": "4822",
        "ok": "2973",
        "ko": "4822"
    },
    "meanResponseTime": {
        "total": "834",
        "ok": "766",
        "ko": "3792"
    },
    "standardDeviation": {
        "total": "764",
        "ok": "621",
        "ko": "524"
    },
    "percentiles1": {
        "total": "463",
        "ok": "450",
        "ko": "3700"
    },
    "percentiles2": {
        "total": "1161",
        "ok": "1078",
        "ko": "4108"
    },
    "percentiles3": {
        "total": "2473",
        "ok": "2073",
        "ko": "4822"
    },
    "percentiles4": {
        "total": "3744",
        "ok": "2723",
        "ko": "4822"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 535,
    "percentage": 66.875
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 70,
    "percentage": 8.75
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 177,
    "percentage": 22.125
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 18,
    "percentage": 2.25
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.67",
        "ok": "2.61",
        "ko": "0.06"
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
