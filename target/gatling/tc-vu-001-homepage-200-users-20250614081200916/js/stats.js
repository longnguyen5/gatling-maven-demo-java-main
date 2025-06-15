var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "400",
        "ok": "400",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "11",
        "ok": "11",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "5314",
        "ok": "5314",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "474",
        "ok": "474",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "589",
        "ok": "589",
        "ko": "-"
    },
    "percentiles1": {
        "total": "410",
        "ok": "410",
        "ko": "-"
    },
    "percentiles2": {
        "total": "597",
        "ok": "597",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1314",
        "ok": "1314",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3511",
        "ok": "3511",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 347,
    "percentage": 86.75
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 28,
    "percentage": 7.000000000000001
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 25,
    "percentage": 6.25
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.23",
        "ok": "3.23",
        "ko": "-"
    }
},
contents: {
"req_access-homepage-2121328426": {
        type: "REQUEST",
        name: "Access Homepage",
path: "Access Homepage",
pathFormatted: "req_access-homepage-2121328426",
stats: {
    "name": "Access Homepage",
    "numberOfRequests": {
        "total": "200",
        "ok": "200",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "11",
        "ok": "11",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "5314",
        "ok": "5314",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "234",
        "ok": "234",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "531",
        "ok": "531",
        "ko": "-"
    },
    "percentiles1": {
        "total": "89",
        "ok": "89",
        "ko": "-"
    },
    "percentiles2": {
        "total": "187",
        "ok": "187",
        "ko": "-"
    },
    "percentiles3": {
        "total": "749",
        "ok": "749",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3511",
        "ok": "3511",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 191,
    "percentage": 95.5
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 5,
    "percentage": 2.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 4,
    "percentage": 2.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.61",
        "ok": "1.61",
        "ko": "-"
    }
}
    },"req_access-homepage-347374531": {
        type: "REQUEST",
        name: "Access Homepage Redirect 1",
path: "Access Homepage Redirect 1",
pathFormatted: "req_access-homepage-347374531",
stats: {
    "name": "Access Homepage Redirect 1",
    "numberOfRequests": {
        "total": "200",
        "ok": "200",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "336",
        "ok": "336",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "4603",
        "ok": "4603",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "715",
        "ok": "715",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "544",
        "ok": "544",
        "ko": "-"
    },
    "percentiles1": {
        "total": "526",
        "ok": "526",
        "ko": "-"
    },
    "percentiles2": {
        "total": "717",
        "ok": "717",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1650",
        "ok": "1650",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3968",
        "ok": "3968",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 156,
    "percentage": 78.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 23,
    "percentage": 11.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 21,
    "percentage": 10.5
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.61",
        "ok": "1.61",
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
