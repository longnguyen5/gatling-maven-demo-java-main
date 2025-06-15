var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "100",
        "ok": "100",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "334",
        "ok": "334",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1680",
        "ok": "1680",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "606",
        "ok": "606",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "246",
        "ok": "246",
        "ko": "-"
    },
    "percentiles1": {
        "total": "515",
        "ok": "515",
        "ko": "-"
    },
    "percentiles2": {
        "total": "686",
        "ok": "686",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1149",
        "ok": "1149",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1680",
        "ok": "1680",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 84,
    "percentage": 84.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 12,
    "percentage": 12.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 4,
    "percentage": 4.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.67",
        "ok": "1.67",
        "ko": "-"
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
        "total": "100",
        "ok": "100",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "334",
        "ok": "334",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1680",
        "ok": "1680",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "606",
        "ok": "606",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "246",
        "ok": "246",
        "ko": "-"
    },
    "percentiles1": {
        "total": "515",
        "ok": "515",
        "ko": "-"
    },
    "percentiles2": {
        "total": "686",
        "ok": "686",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1149",
        "ok": "1149",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1680",
        "ok": "1680",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 84,
    "percentage": 84.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 12,
    "percentage": 12.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 4,
    "percentage": 4.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.67",
        "ok": "1.67",
        "ko": "-"
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
