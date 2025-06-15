var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2500",
        "ok": "2338",
        "ko": "162"
    },
    "minResponseTime": {
        "total": "135",
        "ok": "145",
        "ko": "135"
    },
    "maxResponseTime": {
        "total": "10170",
        "ok": "1991",
        "ko": "10170"
    },
    "meanResponseTime": {
        "total": "459",
        "ok": "352",
        "ko": "1995"
    },
    "standardDeviation": {
        "total": "645",
        "ok": "355",
        "ko": "1440"
    },
    "percentiles1": {
        "total": "212",
        "ok": "209",
        "ko": "2196"
    },
    "percentiles2": {
        "total": "384",
        "ok": "355",
        "ko": "2512"
    },
    "percentiles3": {
        "total": "1932",
        "ok": "1338",
        "ko": "4045"
    },
    "percentiles4": {
        "total": "3431",
        "ok": "1807",
        "ko": "7415"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2140,
    "percentage": 85.6
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 51,
    "percentage": 2.04
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 147,
    "percentage": 5.88
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 162,
    "percentage": 6.4799999999999995
},
    "meanNumberOfRequestsPerSecond": {
        "total": "16.34",
        "ok": "15.28",
        "ko": "1.06"
    }
},
contents: {
"req_api-request-221612617": {
        type: "REQUEST",
        name: "API Request",
path: "API Request",
pathFormatted: "req_api-request-221612617",
stats: {
    "name": "API Request",
    "numberOfRequests": {
        "total": "2500",
        "ok": "2338",
        "ko": "162"
    },
    "minResponseTime": {
        "total": "135",
        "ok": "145",
        "ko": "135"
    },
    "maxResponseTime": {
        "total": "10170",
        "ok": "1991",
        "ko": "10170"
    },
    "meanResponseTime": {
        "total": "459",
        "ok": "352",
        "ko": "1995"
    },
    "standardDeviation": {
        "total": "645",
        "ok": "355",
        "ko": "1440"
    },
    "percentiles1": {
        "total": "212",
        "ok": "209",
        "ko": "2196"
    },
    "percentiles2": {
        "total": "384",
        "ok": "355",
        "ko": "2512"
    },
    "percentiles3": {
        "total": "1926",
        "ok": "1332",
        "ko": "4042"
    },
    "percentiles4": {
        "total": "3402",
        "ok": "1812",
        "ko": "7415"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2140,
    "percentage": 85.6
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 51,
    "percentage": 2.04
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 147,
    "percentage": 5.88
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 162,
    "percentage": 6.4799999999999995
},
    "meanNumberOfRequestsPerSecond": {
        "total": "16.34",
        "ok": "15.28",
        "ko": "1.06"
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
